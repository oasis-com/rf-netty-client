package com.oasis

import com.asia.protobuf.ProductRespOuterClass
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.protobuf.ProtobufDecoder
import io.netty.handler.codec.protobuf.ProtobufEncoder
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender

class ClientChannelHandler : ChannelInitializer<SocketChannel>() {

    override fun initChannel(ch: SocketChannel) {
        ch.pipeline().addLast(ProtobufVarint32FrameDecoder())
        ch.pipeline().addLast(ProtobufDecoder(ProductRespOuterClass.ProductResp.getDefaultInstance()))
        ch.pipeline().addLast(ProtobufVarint32LengthFieldPrepender())
        ch.pipeline().addLast(ProtobufEncoder())
//        ch.pipeline().addLast(RFClientHandler())
        ch.pipeline().addLast(ProReqClientHandler())
    }

}