package com.tua.wanchalerm.notification.fractory

import com.tua.wanchalerm.notification.constant.Response
import com.tua.wanchalerm.notification.controller.response.GeneralResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class ResponseFactory {

    fun success(data: Any? = null, clazz: Class<*>? = null) : ResponseEntity<*> {
        GeneralResponse<Any>().apply {
            code = Response.SUCCESS_CODE
            data?.let {
                this.data = clazz?.cast(data)
            }

        }.run {
            return ResponseEntity.ok(this)
        }
    }

    fun error(httpStatus: HttpStatus, errorCode: String, data: Any? = null, clazz: Class<*>? = null) : ResponseEntity<*> {
        GeneralResponse<Any>().apply {
            code = errorCode
            data?.let {
                this.data = clazz?.cast(data)
            }
        }.run {
           return ResponseEntity(this, httpStatus)
        }
    }
}