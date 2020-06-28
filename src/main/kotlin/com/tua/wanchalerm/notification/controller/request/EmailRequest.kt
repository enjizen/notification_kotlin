package com.tua.wanchalerm.notification.controller.request

import com.fasterxml.jackson.annotation.JsonProperty

data class EmailRequest (
        @JsonProperty("email_to")
        val emailTo: List<String>?,
        @JsonProperty("email_cc")
        val emailCc: List<String>?,
        @JsonProperty("email_bcc")
        val emailBcc: List<String>?,
        @JsonProperty("subject")
        val subject: String,
        @JsonProperty("content")
        val content: Map<String, String>?
)