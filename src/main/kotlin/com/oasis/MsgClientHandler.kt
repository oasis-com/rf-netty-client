package com.oasis

import com.asia.protobuf.ProductReqProto
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter

class MsgClientHandler : ChannelInboundHandlerAdapter() {

    override fun channelActive(ctx: ChannelHandlerContext) {
        println("连接成功")

        ctx.writeAndFlush(proReq())
    }

    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        println("接收到客户端消息: $msg")

        if (msg is ProductReqProto.ProductReq) {
            println("ok")
        }
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        cause?.printStackTrace()
        ctx?.close()
    }

    private fun proReq(): ProductReqProto.ProductReq? {
        return ProductReqProto.ProductReq.newBuilder()
            .setReqId(1)
            .setUserName("Tom")
            .setProductName("Note")
            .addAllAddress(listOf("BeiJing", "ShangHai"))
            .build()
    }
}