package com.tua.wanchalerm.notification.service

import com.tua.wanchalerm.notification.model.Email
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context

@Service
class EmailService {
    val log = LogManager.getLogger()!!

    @Autowired
    private lateinit var mailSender: JavaMailSender

    @Autowired
    private lateinit var templateEngine: TemplateEngine



    fun sent(email: Email)  {
            val ctx = Context().apply {
                email.content?.forEach (this::setVariable)
            }

            val content = templateEngine.process(email.template.fileName, ctx)

            val mimeMessage = mailSender.createMimeMessage()
            val message = MimeMessageHelper(mimeMessage, true, "UTF-8")

            message.apply {
                with(email) {
                    subject?.let { setSubject(it) }
                    emailTo?.let { setTo(it.toTypedArray()) }
                    emailCc?.let { setCc(it.toTypedArray()) }
                    emailBcc?.let { setBcc(it.toTypedArray()) }
                }
                setText(content, true)
            }

            mailSender.send(mimeMessage)
    }

}