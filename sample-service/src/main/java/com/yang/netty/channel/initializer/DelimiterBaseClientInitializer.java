package com.yang.netty.channel.initializer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import com.yang.netty.client.handler.EchoClientHandler;

/**
 * 自定义分隔符解码器,这里采用的是$_为分隔符
 * @author yangyaming
 */
public class DelimiterBaseClientInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
		channel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
		channel.pipeline().addLast(new StringDecoder());
		channel.pipeline().addLast(new EchoClientHandler());
	}
}
