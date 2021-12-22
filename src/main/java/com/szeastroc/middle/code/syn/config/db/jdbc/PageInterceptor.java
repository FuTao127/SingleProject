package com.szeastroc.middle.code.syn.config.db.jdbc;


import com.szeastroc.middle.code.syn.utils.ReflectUtil;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 分页拦截器
 *
 * @author Admin
 *
 */
@Intercepts({
		@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class PageInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		if (invocation.getTarget() instanceof RoutingStatementHandler) {
			RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
			StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(statementHandler, "delegate");
			MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(delegate, "mappedStatement");
			BoundSql boundSql = delegate.getBoundSql();
			Object parameterObject = boundSql.getParameterObject();
			Page page = findPageObject(parameterObject);
			if (page != null) {
				String sql = boundSql.getSql();
				String sqls[] = PageSqlKit.parsePageSql(sql);
				Connection connection = (Connection) invocation.getArgs()[0];
				String countSql = "select count(1) " + sqls[1];
				BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql,
						boundSql.getParameterMappings(), parameterObject);
				PreparedStatement preparedStatement = connection.prepareStatement(countSql);
				setParameters(preparedStatement, mappedStatement, countBS, parameterObject);
				ResultSet resultSet = preparedStatement.executeQuery();
				int count = 0;
				if (resultSet.next()) {
					count = resultSet.getInt(1);
				}
				resultSet.close();
				preparedStatement.close();
				page.setTotal(count);
				page.setTotalPage(count / page.getLimit() + 1);
				ReflectUtil.setFieldValue(boundSql, "sql", generateMysqlPageSql(sql, page));
			}

			Limit limit = findLimitObject(parameterObject);
			if (limit != null) {
				String sql = boundSql.getSql();
				ReflectUtil.setFieldValue(boundSql, "sql", generateMysqlLimitSql(sql, limit));
			}
		}
		return invocation.proceed();
	}

	private String generateMysqlPageSql(String sql, Page page) {
		StringBuffer pageSql = new StringBuffer();
		int currentResult = (page.getPage() - 1) * page.getLimit();
		pageSql.append(sql);

		List<Sorter> sorters = page.getSorter();

		if (sorters != null && sorters.size() > 0) {
			pageSql.append(" order by ");
			for (Sorter sorter : sorters) {
				pageSql.append(humpToUnderline(sorter.getField())).append(" ").append(sorter.getOrder()).append(",");
			}
			pageSql = pageSql.deleteCharAt(pageSql.length() - 1);
		}

		pageSql.append(" limit " + currentResult + "," + page.getLimit());
		return pageSql.toString();
	}

	private String generateMysqlLimitSql(String sql, Limit limit) {
		StringBuffer limitSql = new StringBuffer(sql);
		limitSql.append(" limit " + limit.getOffset() + "," + limit.getLimit());
		return limitSql.toString();
	}

	public  String humpToUnderline(String para) {
		StringBuilder sb = new StringBuilder(para);
		int temp = 0;// 定位
		for (int i = 0; i < para.length(); i++) {
			if (Character.isUpperCase(para.charAt(i))) {
				sb.insert(i + temp, "_");
				temp += 1;
			}
		}
		return sb.toString().toLowerCase();
	}

	/**
	 * 设置请求参数
	 *
	 * @param ps
	 * @param mappedStatement
	 * @param boundSql
	 * @param parameterObject
	 * @throws SQLException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,
							   Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings != null) {
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)
							&& boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value)
									.getValue(propertyName.substring(prop.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject.getValue(propertyName);
					}
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName
								+ " of statement " + mappedStatement.getId());
					}
					typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
				}
			}
		}
	}


	/**
	 * 获取参数中的Page对像
	 *
	 * @return
	 */
	private Limit findLimitObject(Object parameterObj) {
		if (parameterObj instanceof Limit) {
			return (Limit) parameterObj;
		} else if (parameterObj instanceof Map) {
			for (Object val : ((Map<?, ?>) parameterObj).values()) {
				if (val instanceof Page) {
					return (Limit) val;
				}
			}
		}
		return null;

	}

	/**
	 * 获取参数中的Page对像
	 *
	 * @return
	 */
	private Page findPageObject(Object parameterObj) {
		if (parameterObj instanceof Page) {
			return (Page) parameterObj;
		} else if (parameterObj instanceof Map) {
			for (Object val : ((Map<?, ?>) parameterObj).values()) {
				if (val instanceof Page) {
					return (Page) val;
				}
			}
		}
		return null;

	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}

}
