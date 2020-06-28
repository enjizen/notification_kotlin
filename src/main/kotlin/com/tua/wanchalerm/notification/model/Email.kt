package com.tua.wanchalerm.notification.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.tua.wanchalerm.notification.enums.EmailTemplate

class Email (
    val emailTo: List<String>?,
    val emailCc: List<String>?,
    val emailBcc: List<String>?,
    val subject: String?,
    val content: Map<String, String>?,
    val template: EmailTemplate
)