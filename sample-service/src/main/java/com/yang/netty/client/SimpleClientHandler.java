package com.yang.netty.client;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleClientHandler extends ChannelHandlerAdapter {
	protected static final Logger logger = LoggerFactory.getLogger(SimpleClientHandler.class);
	
	private final byte[] writeBytes ;
	
	private int counter = 0;
	
	public SimpleClientHandler(){
		 writeBytes = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		logger.info("channelActive.................."); 
		
		ByteBuf buf = null;
		for(int i=0;i< 100;i++ ){
			buf = Unpooled.buffer(writeBytes.length);
			buf.writeBytes(writeBytes);
			ctx.writeAndFlush(buf);
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.info("channelRead.................."); 
		
		String body = (String)msg;
		++counter;
		logger.info("读取到的消息为：" + body); 
		logger.info("counter：" + counter); 
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.error("ChannelHandle 异常！");
		ctx.close();
	}
	
}
