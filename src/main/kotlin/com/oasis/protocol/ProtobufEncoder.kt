package com.oasis.protocol

import com.asia.protobuf.ProductReqProto
import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder

class ProtobufEncoder : MessageToByteEncoder<ProductReqProto.ProductReq>() {

    override fun encode(ctx: ChannelHandlerContext, msg: ProductReqProto.ProductReq, out: ByteBuf) {
        // 将对象转换为 byte
        val bArray = msg.toByteArray()

        // 读取消息的长度
        val length = bArray.size

        val buf = Unpooled.buffer(2 + length)

        // 先将消息长度写入（消息头）
        buf.writeShort(length)
        // 写入commandId
        buf.writeInt(10)
        // 消息体包含我们要发送的数据
        buf.writeBytes(bArray)

        out.writeBytes(buf)
    }
}