package com.hbcun.business.bs.server.omstrans;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
//import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class OmsTransServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        // Create a default pipeline implementation.
        ChannelPipeline pipeline = ch.pipeline();     
 
     
        /**
         * http-response解码器
         * http服务器端对response编码
         */
        pipeline.addLast("encoder", new HttpResponseEncoder());
        pipeline.addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8));
        /**
         * http-request解码器
         * http服务器端对request解码
         */
        pipeline.addLast("decoder", new HttpRequestDecoder());
        pipeline.addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));
        /**
         * 压缩
         * Compresses an HttpMessage and an HttpContent in gzip or deflate encoding
         * while respecting the "Accept-Encoding" header.
         * If there is no matching encoding, no compression is done.
         */
        pipeline.addLast("deflater", new HttpContentCompressor());
 
        pipeline.addLast("handler", new OmsTransServerHandler());
    }
}