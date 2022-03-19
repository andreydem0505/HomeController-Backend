package com.dementiev_a.homecontrollerbackend.controllers

import com.dementiev_a.homecontrollerbackend.dto.NotifyRequest
import com.dementiev_a.homecontrollerbackend.dto.VerifyRequest
import com.dementiev_a.homecontrollerbackend.dto.VerifyResponse
import com.dementiev_a.homecontrollerbackend.internationalization.Dictionary
import com.dementiev_a.homecontrollerbackend.models.User
import com.dementiev_a.homecontrollerbackend.storages.firebase.FirestoreService
import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid

@RestController
class MainController {
    @Autowired
    lateinit var firestoreService: FirestoreService

    @Autowired
    lateinit var bot: Bot

    @PostMapping("/verify")
    fun verify(@Valid @RequestBody body: VerifyRequest): VerifyResponse {
        return VerifyResponse(firestoreService.hasUser(body.key!!))
    }

    @PostMapping("/notify")
    fun verify(@Valid @RequestBody body: NotifyRequest) {
        val user: User
        try {
            user = firestoreService.getUser(body.key!!)
        } catch (e: NullPointerException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid key")
        }
        bot.sendMessage(
            chatId = ChatId.fromId(user.id),
            text = Dictionary.get(user.lang).warningMessage.format(body.sensor)
        )
    }
}