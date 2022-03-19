package com.dementiev_a.homecontrollerbackend.storages.firebase

import com.dementiev_a.homecontrollerbackend.models.User
import com.dementiev_a.homecontrollerbackend.storages.Database
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import java.io.FileInputStream
import java.io.InputStream

@Service
class FirestoreService : Database {
    private lateinit var db: Firestore

    @Bean
    fun getDB() {
        val serviceAccount: InputStream = FileInputStream("firebase.json")
        val credentials = GoogleCredentials.fromStream(serviceAccount)
        val options = FirebaseOptions.builder()
            .setCredentials(credentials)
            .build()
        FirebaseApp.initializeApp(options)

        db = FirestoreClient.getFirestore()
    }

    override fun saveUser(id: Long, lang: String): String {
        val documents = db.collection("users").whereEqualTo("id", id).get().get().documents
        if (documents.size > 0) {
            return documents[0].id
        }
        val docRef = db.collection("users").document()
        docRef.set(mapOf("id" to id, "lang" to lang))
        return docRef.id
    }

    override fun hasUser(key: String): Boolean {
        return db.collection("users").document(key).get().get().exists()
    }

    override fun getUser(key: String): User {
        val document = db.collection("users").document(key).get().get()
        return User(id = document.get("id") as Long, lang = document.get("lang") as String)
    }
}