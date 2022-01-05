package com.oasis

import com.asia.protobuf.ProductReqProto

object ProductReqProto {

    private fun encode(req: ProductReqProto.ProductReq): ByteArray? {
        return req.toByteArray()
    }

    private fun decode(body: ByteArray): ProductReqProto.ProductReq? {
        return ProductReqProto.ProductReq.parseFrom(body)
    }

    private fun createSubscribeReq(): ProductReqProto.ProductReq? {
        return ProductReqProto.ProductReq.newBuilder()
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