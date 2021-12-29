package com.szeastroc.middle.code.syn.annotation.aspect;

import com.szeastroc.middle.code.syn.constants.EastrocException;
import com.szeastroc.middle.code.syn.utils.Result;
import com.szeastroc.middle.code.syn.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

/**
 * @Description:日志切面
 * @Author: futao
 * @Date: 2021/12/20
 **/
@Aspect
@Component
@Slf4j
public class logAspect {

    @Around("@annotation(com.szeastroc.middle.code.syn.annotation.LogSection)")
    public Result around(ProceedingJoinPoint point){
        Result result = null;
        String clssMethodName = point.getSignature().getDeclaringTypeName()+"."+point.getSignature().getName();
        Object[] paramValues = point.getArgs();
        String[] paramNames = ((CodeSignature) point.getSignature()).getParameterNames();
        StringBuffer inParam = new StringBuffer();
        inParam.append(clssMethodName).append(" 入参 ：");
        for(int i=0;i<paramNames.length;i++){
            inParam.append(paramNames[i]).append(":").append(paramValues[i]);
        }
        log.info(inParam.toString());
        long beginTime = System.currentTimeMillis();
        try {
            Object proceed = point.proceed();
            long endTime = System.currentTimeMillis();
            if(proceed instanceof Result){
                result = (Result)proceed;
            }
            log.info(clssMethodName+" 总耗时:"+(endTime - beginTime)+"毫秒。出参 ："+result.toString());
        }catch (EastrocException e){
            long endTime = System.currentTimeMillis();
            log.info(clssMethodName+" 总耗时:"+(endTime - beginTime)+"毫秒。出参 ："+ResultUtils.renderError(e.getMessage()));
            return ResultUtils.renderError(e.getMessage(),e.getCode());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return ResultUtils.renderError(throwable.getMessage());
        }
        return result;
    }

}
