package com.dementiev_a.homecontrollerbackend.internationalization

object Dictionary {
    private val map = mapOf(
        "en" to WordsSet(
            authCode = "Your key: `%s`",
            warningMessage = "Warning from sensor \"%s\"!"
        ),
        "ru" to WordsSet(
            authCode = "Ваш ключ: `%s`",
            warningMessage = "Тревога с датчика \"%s\"!"
        )
    )

    fun get(code: String): WordsSet {
        return if (code in map) map[code]!! else map["en"]!!
    }
}