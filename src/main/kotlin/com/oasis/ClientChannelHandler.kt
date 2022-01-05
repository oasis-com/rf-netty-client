package com.oasis

import com.oasis.protocol.ProtobufDecoder
import com.oasis.protocol.ProtobufEncoder
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel

class ClientChannelHandler : ChannelInitializer<SocketChannel>() {

    override fun initChannel(ch: SocketChannel) {
        // ch.pipeline().addLast(ProtobufVarint32FrameDecoder())
        // ch.pipeline().addLast(ProtobufDecoder(ProductRespProto.ProductResp.getDefaultInstance()))
        // ch.pipeline().addLast(ProtobufVarint32LengthFieldPrepender())
        // ch.pipeline().addLast(ProtobufEncoder())

        ch.pipeline().addLast(ProtobufDecoder())
        ch.pipeline().addLast(ProtobufEncoder())

        // ch.pipeline().addLast(RFClientHandler())
        // ch.pipeline().addLast(ProReqClientHandler())

        ch.pipeline().addLast(MsgClientHandler())
    }

}