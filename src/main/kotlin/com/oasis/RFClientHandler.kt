package com.oasis

import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter

class RFClientHandler : ChannelInboundHandlerAdapter() {

    override fun channelActive(ctx: ChannelHandlerContext) {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello world", Charsets.UTF_8))
    }

    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        val buf = msg as ByteBuf

        val req = ByteArray(buf.readableBytes())
        buf.readBytes(req)

        val body = String(req, Charsets.UTF_8)
        println("客户端接受：$body")
    }

    override fun channelInactive(ctx: ChannelHandlerContext) {
        println("服务端断开链接" + ctx.channel().localAddress().toString())
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        cause?.printStackTrace()
        ctx?.close()
    }
}