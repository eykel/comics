package com.example.comics.data.util

import java.math.BigInteger
import java.security.MessageDigest
import java.util.Date

class Authenticator {
    private val publicKey: String = "00a34b99ef32cef640885260b7e1821c"
    private val privateKey: String = "148d35a0e94e32593bb6086bdcb1e08c17b40842"

    fun createParams(): AuthParams {
        val ts = Date().time.toString()
        val hash = md5("$ts$privateKey$publicKey")
        return AuthParams(ts, publicKey, hash)
    }

    private fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}

data class AuthParams(val ts: String, val apikey: String, val hash: String)