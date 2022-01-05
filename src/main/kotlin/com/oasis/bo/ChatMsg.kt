package com.oasis.bo

data class ChatMsg(
    val user: User,
    val msgId: Long,
    val from: String,
    val to: String,
    val time: Long,
    val msgType: MsgType,
    val content: String,
    val url: String,
    val property: String,
    val fromNick: String,
    val json: String
)

enum class MsgType {
    TEXT,
    AUDIO,
    VIDEO,
    POS,
    OTHER
}