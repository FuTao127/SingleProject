package com.szeastroc.middle.code.syn.utils;


import com.szeastroc.middle.code.syn.constants.EastrocException;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

	private static Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(true)
			.buildValidatorFactory().getValidator();

	public static boolean isEmail(String email) {
		if (StringUtils.isEmpty(email)) {
			return false;
		}
		String regEx = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(email);
		return matcher.find();
	}
	
	public static boolean isPhone(String phone) {
		if (StringUtils.isEmpty(phone)) {
			return false;
		}
		String regEx = "^1[34578]\\d{9}$";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(phone);
		return matcher.find();
	}

	public static <T> void validate(T obj) {
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
		// 抛出检验异常
		if (constraintViolations.size() > 0) {
			throw new EastrocException(constraintViolations.iterator().next().getMessage());
		}
	}
	
	
}
