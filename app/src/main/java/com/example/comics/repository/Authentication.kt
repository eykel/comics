package com.example.comics.repository

import com.example.comics.view.md5
import java.util.Date

class Authentication {
    val publicKey: String = "00a34b99ef32cef640885260b7e1821c"
    val privateKey: String = "148d35a0e94e32593bb6086bdcb1e08c17b40842"
    val ts:String = Date().toString()
    val hash :String =  md5(ts+privateKey+publicKey)
}