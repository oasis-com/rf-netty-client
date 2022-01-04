package com.oasis

object App {

    @JvmStatic
    fun main(args: Array<String>) {
        RFClient().connect(10086, "127.0.0.1")
    }

}