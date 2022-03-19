package com.dementiev_a.homecontrollerbackend.telegram

import com.dementiev_a.homecontrollerbackend.internationalization.Dictionary
import com.dementiev_a.homecontrollerbackend.storages.firebase.FirestoreService
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.ParseMode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Bot {
    @Value("\${my.bot_token}")
    lateinit var telegramToken: String

    @Autowired
    lateinit var firestoreService: FirestoreService

    @Bean
    fun start(): com.github.kotlintelegrambot.Bot {
        val bot = bot {
            token = telegramToken
            dispatch {
                command("start") {
                    val key = firestoreService.saveUser(message.chat.id, message.from!!.languageCode!!)
                    bot.sendMessage(
                        chatId = ChatId.fromId(message.chat.id),
                        text = Dictionary.get(message.from!!.languageCode!!).authCode.format(key),
                        parseMode = ParseMode.MARKDOWN
                    )
                }
            }
        }
        bot.startPolling()
        return bot
    }
}