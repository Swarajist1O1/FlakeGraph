class DummyClass_60874 {
  @Test
  public void testSimpleInjection()
  {
    final String uuid = UUID.randomUUID().toString();
    System.setProperty(uuid + ".type", "redis");
    final Injector injector = Initialization.makeInjectorWithModules(
        GuiceInjectors.makeStartupInjector(), ImmutableList.of(
            binder -> {
              binder.bindConstant().annotatedWith(Names.named("serviceName")).to("druid/test/redis");
              binder.bindConstant().annotatedWith(Names.named("servicePort")).to(0);
              binder.bindConstant().annotatedWith(Names.named("tlsServicePort")).to(-1);

              binder.bind(Cache.class).toProvider(CacheProvider.class);
              JsonConfigProvider.bind(binder, uuid, CacheProvider.class);
            }
        )
    );
    final CacheProvider cacheProvider = injector.getInstance(CacheProvider.class);
    Assert.assertNotNull(cacheProvider);
    Assert.assertEquals(RedisCacheProvider.class, cacheProvider.getClass());
  }

}