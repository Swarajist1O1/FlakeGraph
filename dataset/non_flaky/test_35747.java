class DummyClass_35747 {
  @Test
  public void testHttpPipelining() throws Exception {
    final BlockingQueue<HttpResponseStatus> responseStatuses = new LinkedBlockingQueue<>();
    EventLoopGroup eventGroup = new NioEventLoopGroup();

    Bootstrap bootstrap = new Bootstrap()
      .channel(NioSocketChannel.class)
      .group(eventGroup)
      .handler(new ChannelInitializer<SocketChannel>() {
        @Override
        protected void initChannel(SocketChannel ch) {
          ChannelPipeline pipeline = ch.pipeline();
          pipeline.addLast("codec", new HttpClientCodec());
          pipeline.addLast("aggregator", new HttpObjectAggregator(1048576));
          pipeline.addLast("handler", new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) {
              if (msg instanceof HttpResponse) {
                responseStatuses.add(((HttpResponse) msg).status());
              }
              ReferenceCountUtil.release(msg);
            }

}