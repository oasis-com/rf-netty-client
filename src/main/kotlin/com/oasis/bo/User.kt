package com.oasis.bo

data class User(val uid: String, val devId: String, val token: String, val nickName: String, val platform: PlatType)

enum class PlatType {
    WINDOWS, MAC, ANDROID, IOS, WEB, OTHER
}