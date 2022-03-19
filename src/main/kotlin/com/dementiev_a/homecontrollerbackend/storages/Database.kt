package com.dementiev_a.homecontrollerbackend.storages

import com.dementiev_a.homecontrollerbackend.models.User

interface Database {
    fun saveUser(id: Long, lang: String): String
    fun hasUser(key: String): Boolean
    fun getUser(key: String): User
}