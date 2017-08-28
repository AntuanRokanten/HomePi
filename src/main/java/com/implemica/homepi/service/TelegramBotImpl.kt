package com.implemica.homepi.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.TelegramBotsApi
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import javax.annotation.PostConstruct

/**
 * @author ant
 */
@Component
class TelegramBotImpl @Autowired constructor(
        @Value("\${telegram.bot.token}")
        private val token: String,
        @Value("\${telegram.bot.token}")
        private val username: String) : TelegramLongPollingBot(), TelegramBot {

    override fun notifyMotionDetected() {
        sendApiMethod(SendMessage(0, "Motion is detected"))
    }

    @PostConstruct
    fun init() {
        TelegramBotsApi().registerBot(this)
    }

    override fun getBotToken() = token

    override fun getBotUsername() = username

    override fun onUpdateReceived(update: Update) {
        println("New telegram message received ${update.message}")
    }
}