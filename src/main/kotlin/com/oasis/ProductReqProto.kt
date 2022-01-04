package com.oasis

import com.asia.protobuf.ProductReqOuterClass

object ProductReqProto {

    fun encode(req: ProductReqOuterClass.ProductReq): ByteArray? {
        return req.toByteArray()
    }

    fun decode(body: ByteArray): ProductReqOuterClass.ProductReq? {
        return ProductReqOuterClass.ProductReq.parseFrom(body)
    }

    private fun createSubscribeReq(): ProductReqOuterClass.ProductReq? {
        return ProductReqOuterClass.ProductReq.newBuilder()
            .setReqId(1)
            .setUserName("Tom")
            .setProductName("Note")
            .addAllAddress(listOf("BeiJing", "ShangHai"))
            .build()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val req = createSubscribeReq()
        println(req)

        val encodeReq = encode(req!!)
        println(encodeReq)

        val decodeReq = decode(encodeReq!!)
        println(decodeReq)
    }
}