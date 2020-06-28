package com.tua.wanchalerm.notification.controller

import com.tua.wanchalerm.notification.controller.request.EmailRequest
import com.tua.wanchalerm.notification.enums.EmailTemplate
import com.tua.wanchalerm.notification.fractory.ResponseFactory
import com.tua.wanchalerm.notification.model.Email
import com.tua.wanchalerm.notification.service.EmailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class EmailController {

    @Autowired
    private lateinit var emailService: EmailService

    @Autowired
    private lateinit var responseFactory: ResponseFactory

    @PostMapping("/v1/email")
    fun sendEmail(@RequestParam("template") template: EmailTemplate, @RequestBody request: EmailRequest): ResponseEntity<*> {
        val email =
                with(request) {
                    Email(emailTo = emailTo,
                            emailCc = emailCc,
                            emailBcc = emailBcc,
                            subject = subject,
                            content = content,
                            template = template)
                }

        emailService.sent(email)
        return responseFactory.success();
    }
}
