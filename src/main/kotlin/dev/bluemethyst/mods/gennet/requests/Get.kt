package dev.bluemethyst.mods.gennet.requests

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody


class Get  {
    private val client = OkHttpClient()

     fun get(url: String): Response {
        val request = Request.Builder().url(url).build()
        return client.newCall(request).execute()
    }

     fun post(url: String, body: String): Response {
        val requestBody = body.toRequestBody("application/json; charset=utf-8".toMediaType())
        val request = Request.Builder().url(url).post(requestBody).build()
        return client.newCall(request).execute()
    }

     fun openWebSocket(url: String, listener: WebSocketListener) {
        val request = Request.Builder().url(url).build()
        val ws: WebSocket = client.newWebSocket(request, listener)
    }
}