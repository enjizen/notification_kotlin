package com.tua.wanchalerm.notification.aspectj

import org.apache.logging.log4j.LogManager
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Service

@Aspect
@Service
class LoggingAspectJ {

    val log = LogManager.getLogger(this.javaClass)!!

    /* @Before("execution(* com.tua.wanchalerm.notification.controller.*.*(..))")
    fun logBeforeController(joinPoint: JoinPoint) {
        log.info("******")
        log.info("Start Package : ${joinPoint.signature.declaringTypeName}")
        log.info("Method : ${joinPoint.signature.name}")
    }

    @Before("execution(* com.tua.wanchalerm.notification.service.*.*(..))")
    fun logBefore(joinPoint: JoinPoint) {
        log.info("Start Package : ${joinPoint.signature.declaringTypeName}")
        log.info("Method : ${joinPoint.signature.name}")
    }

    @After("execution(* com.tua.wanchalerm.notification.service.*.*(..))")
    fun logAfter(joinPoint: JoinPoint) {
        log.info("End Package : ${joinPoint.signature.declaringTypeName}")
        log.info("Method : ${joinPoint.signature.name}")
    }

    @After("execution(* com.tua.wanchalerm.notification.controller.*.*(..))")
    fun logControllerAfter(joinPoint: JoinPoint) {
        log.info("")
        log.info("End Package : ${joinPoint.signature.declaringTypeName}")
        log.info("Method : ${joinPoint.signature.name}")
        log.info("******")
    }*/

    @Before("execution(* com.tua.wanchalerm.notification.service.*.*(..))")
    fun logBefore(joinPoint: JoinPoint) {
        log.info("Start Package : ${joinPoint.signature.declaringTypeName}")
        log.info("Method : ${joinPoint.signature.name}")
    }

    @After("execution(* com.tua.wanchalerm.notification.service.*.*(..))")
    fun logAfter(joinPoint: JoinPoint) {
        log.info("End Package : ${joinPoint.signature.declaringTypeName}")
        log.info("Method : ${joinPoint.signature.name}")
    }

}