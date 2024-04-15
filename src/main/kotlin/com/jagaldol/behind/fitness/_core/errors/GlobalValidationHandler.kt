package com.jagaldol.behind.fitness._core.errors

import com.jagaldol.behind.fitness._core.errors.exception.CustomException
import com.jagaldol.behind.fitness._core.errors.exception.ErrorCode
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

@Aspect
@Component
class GlobalValidationHandler {
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    fun postMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    fun putMapping() {
    }

    @Before("postMapping() || putMapping()")
    fun validationAdvice(jp: JoinPoint) {
        jp.args.filterIsInstance<Errors>().forEach {
            if (it.hasErrors()) {
                throw CustomException(
                    ErrorCode.INVALID_REQUEST_DATA,
                    it.fieldErrors.joinToString(separator = ", ") { error ->
                        "${error.defaultMessage}:${error.field}"
                    }
                )
            }
        }
    }
}