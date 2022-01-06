package com.oasis.protocol

import com.asia.protobuf.ProductReqProto
import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

class ProtobufDecoder : ByteToMessageDecoder() {

    override fun decode(ctx: ChannelHandlerContext, bArray: ByteBuf, out: MutableList<Any>) {
        // 标记一下当前的readIndex的位置
        bArray.markReaderIndex()

        // 判断包头长度
        if (bArray.readableBytes() < 2) { // 不够包头
            return
        }

        // 读取消息传送过来的长度
        val length = bArray.readUnsignedShort()

        // 读取commandId
        val commandId = bArray.readInt()

        // 长度如果小于0
        if (length < 0) { // 非法数据，关闭连接
            ctx.close()
        }

        if (length > bArray.readableBytes()) { // 读到的消息长度如果小于传送过来的消息长度
            // 重置读取位置
            bArray.resetReaderIndex()
            return
        }

        val frame = Unpooled.buffer(length)
        bArray.readBytes(frame)

        try {
            val inByte = frame.array()

            // 字节转成对象
            val msg = ProductReqProto.ProductReq.parseFrom(inByte)

            if (msg != null) {
                // protobuf body
                out.add(msg)
            }

        } catch (e: Exception) {
            println("${ctx.channel().remoteAddress()} decode failed.")
            e.printStackTrace()
        }
    }
}