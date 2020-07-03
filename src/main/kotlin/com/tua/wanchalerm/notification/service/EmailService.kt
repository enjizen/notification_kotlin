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
    private lateinit var mainSender: JavaMailSender

    @Autowired
    private lateinit var templateEngine: TemplateEngine



    fun sent(email: Email)  {
            val ctx = Context().apply {
                email.content?.forEach { key, value ->
                    setVariable(key, value)
                }
            }
            val mimeMessage = mainSender.createMimeMessage()
            val message = MimeMessageHelper(mimeMessage, true, "UTF-8")

            with(email) {
                subject?.let { message.setSubject(it) }
                emailTo?.let { message.setTo(it.toTypedArray()) }
                emailCc?.let { message.setCc(it.toTypedArray()) }
                emailBcc?.let { message.setBcc(it.toTypedArray()) }
            }

            val content = templateEngine.process(email.template.fileName, ctx)
            message.setText(content, true)

            mainSender.send(mimeMessage)

    }

}