package com.oasis

import com.asia.protobuf.ProductReqProto
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import java.util.*

class ProReqClientHandler : ChannelInboundHandlerAdapter() {
    private var started = false

    override fun channelActive(ctx: ChannelHandlerContext) {
        ctx.write(proReq(1))
        ctx.flush()
    }

    private fun proReq(reqId: Int): ProductReqProto.ProductReq? {
        return ProductReqProto.ProductReq.newBuilder()
            .setReqId(reqId)
            .setUserName("Tom")
            .setProductName("Note")
            .addAllAddress(listOf("BeiJing", "ShangHai"))
            .build()
    }


    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        println("接收到服务端信息（proResp）：\n$msg")

        val task: TimerTask = object : TimerTask() {
            override fun run() {
                ctx.writeAndFlush(proReq(2))
            }
        }

        if(!started){

            val timer = Timer()

            timer.schedule(task, 1000, 3000)
            started = true
        }
    }

    override fun channelReadComplete(ctx: ChannelHandlerContext?) {
        ctx?.flush()
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        cause?.printStackTrace()
        ctx?.close()
    }
}