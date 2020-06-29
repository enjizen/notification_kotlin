package com.tua.wanchalerm.notification.controller

import com.tua.wanchalerm.notification.constant.Response
import com.tua.wanchalerm.notification.controller.request.EmailRequest
import com.tua.wanchalerm.notification.enums.EmailTemplate
import com.tua.wanchalerm.notification.fractory.ResponseFactory
import com.tua.wanchalerm.notification.model.Email
import com.tua.wanchalerm.notification.service.EmailService
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception
import javax.mail.AuthenticationFailedException

@RestController
class EmailController {

    val log = LogManager.getLogger(this.javaClass)!!

    @Autowired
    private lateinit var emailService: EmailService

    @Autowired
    private lateinit var responseFactory: ResponseFactory

    @PostMapping("/v1/email")
    fun sendEmail(@RequestParam("template") template: EmailTemplate, @RequestBody request: EmailRequest): ResponseEntity<*> {
        log.info("Start send email to {} and template type [{}]", request.emailTo, template.templateName)
        try {
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
        }catch (ex : Exception) {
            log.error("Error", ex)
            return responseFactory.error(HttpStatus.INTERNAL_SERVER_ERROR, Response.SERVER_ERROR)
        }

        log.info("End send email to [{}] and template type [{}]", request.emailTo, template.templateName)
        return responseFactory.success();
    }
}
