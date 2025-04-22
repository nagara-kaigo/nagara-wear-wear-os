package com.example.nagara_wear_wear_os.data.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class KtorApiClient {

    private val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
            })
        }
    }

    private val baseUrl = "https://your-api-url.com/api"

    // --- 1. ログイン ---
    suspend fun login(id: String, password: String): String {
        val response: HttpResponse = client.post("$baseUrl/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(mapOf("loginId" to id, "password" to password))
        }
        return response.bodyAsText()
    }

    // --- 2. テナント一覧取得 ---
    suspend fun getTenants(token: String): String {
        val response = client.get("$baseUrl/tenants") {
            bearerAuth(token)
        }
        return response.bodyAsText()
    }

    // --- 3. ログインユーザー情報取得 ---
    suspend fun getMe(token: String): String {
        val response = client.get("$baseUrl/auth/me") {
            bearerAuth(token)
        }
        return response.bodyAsText()
    }

    // --- 4. チャット一覧取得 ---
    suspend fun getChats(token: String): String {
        val response = client.get("$baseUrl/chats") {
            bearerAuth(token)
        }
        return response.bodyAsText()
    }

    // --- 5. テナントのレジデント一覧取得 ---
    suspend fun getTenantResidents(token: String, tenantUid: String): String {
        val response = client.get("$baseUrl/tenants/$tenantUid/residents") {
            bearerAuth(token)
        }
        return response.bodyAsText()
    }

    // --- 6. レジデント作成 ---
    suspend fun createResident(
        token: String,
        tenantUid: String,
        familyName: String,
        givenName: String,
        familyNameFurigana: String,
        givenNameFurigana: String,
        dateOfBirth: String,
        gender: String,
        admissionDate: String
    ): String {
        val requestBody = ResidentCreateRequest(
            familyName,
            givenName,
            familyNameFurigana,
            givenNameFurigana,
            dateOfBirth,
            gender,
            admissionDate
        )
        val response = client.post("$baseUrl/tenants/$tenantUid/residents") {
            bearerAuth(token)
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }
        return response.bodyAsText()
    }
}

// --- リクエストボディのデータクラス ---
@Serializable
data class ResidentCreateRequest(
    val familyName: String,
    val givenName: String,
    val familyNameFurigana: String,
    val givenNameFurigana: String,
    val dateOfBirth: String,
    val gender: String,
    val admissionDate: String
)