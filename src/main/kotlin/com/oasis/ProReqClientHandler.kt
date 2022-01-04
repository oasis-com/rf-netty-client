package com.oasis

import com.asia.protobuf.ProductReqOuterClass
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter

class ProReqClientHandler : ChannelInboundHandlerAdapter() {

    override fun channelActive(ctx: ChannelHandlerContext) {
        ctx.write(proReq())
        ctx.flush()
    }

    private fun proReq(): ProductReqOuterClass.ProductReq? {
        return ProductReqOuterClass.ProductReq.newBuilder()
            .setReqId(1)
            .setUserName("Tom")
            .setProductName("Note")
            .addAllAddress(listOf("BeiJing", "ShangHai"))
            .build()
    }

    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        println("接收到服务端信息（proResp）：$msg")
    }

    override fun channelReadComplete(ctx: ChannelHandlerContext?) {
        ctx?.flush()
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        cause?.printStackTrace()
        ctx?.close()
    }
}