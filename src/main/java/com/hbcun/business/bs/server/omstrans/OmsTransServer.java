package com.hbcun.business.bs.server.omstrans;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.hbcun.business.bs.constant.OmsTranConfig;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class OmsTransServer {

	private final int port;
	private final String path;

	public OmsTransServer(int port, String path) {
		this.port = port;
		this.path = path;
	}

	public void run() {
		if (!this.path.equalsIgnoreCase(OmsTranConfig.OmsTransServer))
			return;
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new OmsTransServerChannelInitializer());

			Channel ch = bootstrap.bind(port).sync().channel();			
			System.out.println("server start:");	
			ch.closeFuture().sync();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) {
		int port;
		String path;
		if (args.length > 1) {
			port = Integer.parseInt(args[0]);
			path = args[1].toString();
		} else {
			port = 8080;
			path = "OmsTransServer";
		}
		new OmsTransServer(port, path).run();
	}
}
