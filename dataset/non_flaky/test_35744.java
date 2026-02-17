class DummyClass_35744 {
  @Test
  public void testGuiceInjection() {
    CConfiguration cConf = CConfiguration.create();

    Injector injector = RouterMain.createGuiceInjector(cConf);
    Assert.assertNotNull(injector);

    NettyRouter router = injector.getInstance(NettyRouter.class);
    Assert.assertNotNull(router);
  }

}