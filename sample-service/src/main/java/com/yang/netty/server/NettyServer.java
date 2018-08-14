package com.yang.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yang.netty.client.handler.NettyClientHandler;
import com.yang.netty.factory.initializer.ChannelInitializerFactory;

public class NettyServer {
	protected static final Logger logger = LoggerFactory.getLogger(NettyClientHandler.class);
	
	private ChannelInitializerFactory factory;
	
	public NettyServer(){
		factory = new ChannelInitializerFactory();
	}
	
	public NettyServer(ChannelInitializerFactory factory){
		this.factory =  factory;
	}
	
	public void bind(int port){
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(bossGroup,workerGroup)
				 .channel(NioServerSocketChannel.class)
				 .option(ChannelOption.SO_BACKLOG, 1024)
				 .childHandler(factory.serverInitializer());
		
		logger.info("server start");
		
			ChannelFuture channelFuture = bootstrap.bind(port).sync();
			
		logger.info("server is listening");
		
			channelFuture.channel().closeFuture().sync();
			
		logger.info("server stop");
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
