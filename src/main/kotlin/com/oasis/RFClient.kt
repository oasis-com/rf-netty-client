package com.oasis

import io.netty.bootstrap.Bootstrap
import io.netty.buffer.PooledByteBufAllocator
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.logging.LogLevel
import io.netty.handler.logging.LoggingHandler

class RFClient {

    fun connect(port: Int, host: String) {
        // 配置客户端 NIO 线程组
        val group = NioEventLoopGroup()

        try {
            val b = Bootstrap()
            b.group(group)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .channel(NioSocketChannel::class.java)
                .handler(LoggingHandler(LogLevel.INFO))
                .handler(ClientChannelHandler())

            val f = b.connect(host, port).sync()

            // 等待客户端链路关闭
            f.channel().closeFuture().sync()
        } finally {
            // 优雅退出，释放NIO线程组
            group.shutdownGracefully()
        }
    }
}