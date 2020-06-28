package com.tua.wanchalerm.notification.fractory

import com.tua.wanchalerm.notification.constant.Response
import com.tua.wanchalerm.notification.controller.response.GeneralResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class ResponseFactory {

    fun success(value: Any? = null, clazz: Class<*>? = null) : ResponseEntity<*> {
        GeneralResponse<Any>().apply {
            code = Response.SUCCESS_CODE
            value?.let {
                data = clazz?.cast(value)
            }

        }.run {
            return ResponseEntity.ok(this)
        }
    }

    fun error(httpStatus: HttpStatus, errorCode: String) : ResponseEntity<*> {
        GeneralResponse<Any>().apply {
            code = errorCode
        }.run {
           return ResponseEntity(this, httpStatus)
        }
    }
}