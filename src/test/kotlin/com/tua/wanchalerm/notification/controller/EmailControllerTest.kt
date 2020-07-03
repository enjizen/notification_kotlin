package com.tua.wanchalerm.notification.controller

import com.tua.wanchalerm.notification.controller.request.EmailRequest
import com.tua.wanchalerm.notification.controller.response.GeneralResponse
import com.tua.wanchalerm.notification.enums.EmailTemplate
import com.tua.wanchalerm.notification.fractory.ResponseFactory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.*

@RunWith(MockitoJUnitRunner::class)
class EmailControllerTest {

    @InjectMocks
    private lateinit var emailController: EmailController

    @Mock lateinit var responseFactory: ResponseFactory


   @Before
   fun setup(){
       `when`(responseFactory.success()).thenCallRealMethod()
   }


     @Test
    fun `sendEmail success`() {
         val emailTo = arrayListOf("enjizen.r@gmail.com")
         val emailCc = arrayListOf("enjizen.r@gmail.com")
         val emailBcc = arrayListOf("enjizen.r@gmail.com")
         val subject = "subject"
         val content =  hashMapOf<String, String>().apply {
             this["key"] = "value"
             this["key2"] = "value2"
         }

         val emailRequest = EmailRequest(emailTo = emailTo, emailCc = emailCc, emailBcc = emailBcc, subject = subject, content = content)

         val response = emailController.sendEmail(EmailTemplate.OTP, emailRequest)
         val result = response.body as GeneralResponse<*>

         assertNotNull(response)
         assertEquals(200, response.statusCode.value())
         assertEquals("success", result.code)
    }
}