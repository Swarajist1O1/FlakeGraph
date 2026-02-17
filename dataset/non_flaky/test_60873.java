class DummyClass_60873 {
  @Test
  public void testBasicInjection() throws Exception
  {
    String json = "{ \"host\": \"localhost\", \"port\": 6379, \"expiration\": 3600}";
    final RedisCacheConfig config = new ObjectMapper().readValue(json, RedisCacheConfig.class);

    Injector injector = Initialization.makeInjectorWithModules(
        GuiceInjectors.makeStartupInjector(), ImmutableList.of(
            binder -> {
              binder.bindConstant().annotatedWith(Names.named("serviceName")).to("druid/test/redis");
              binder.bindConstant().annotatedWith(Names.named("servicePort")).to(0);
              binder.bindConstant().annotatedWith(Names.named("tlsServicePort")).to(-1);

              binder.bindConstant().annotatedWith(Names.named("host")).to("localhost");
              binder.bindConstant().annotatedWith(Names.named("port")).to(6379);

              binder.bind(RedisCacheConfig.class).toInstance(config);
              binder.bind(Cache.class).toProvider(RedisCacheProviderWithConfig.class).in(ManageLifecycle.class);
            }
        )
    );
    Lifecycle lifecycle = injector.getInstance(Lifecycle.class);
    lifecycle.start();
    try {
      Cache cache = injector.getInstance(Cache.class);
      Assert.assertEquals(RedisStandaloneCache.class, cache.getClass());
    }
    finally {
      lifecycle.stop();
    }
  }

}