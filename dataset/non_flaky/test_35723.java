class DummyClass_35723 {
  @Test
  public void testReset() {
    Cancellable cancellable = LoggingContextAccessor.setLoggingContext(
      new GenericLoggingContext(OLD_NS, OLD_APP, OLD_ENTITY));
    Assert.assertEquals(MDC.get(NamespaceLoggingContext.TAG_NAMESPACE_ID), OLD_NS);
    Assert.assertEquals(MDC.get(ApplicationLoggingContext.TAG_APPLICATION_ID), OLD_APP);
    Assert.assertEquals(MDC.get(GenericLoggingContext.TAG_ENTITY_ID), OLD_ENTITY);

    final Cancellable cancellable2 =
      LoggingContextAccessor.setLoggingContext(new GenericLoggingContext(NS, APP, ENTITY));

    Assert.assertEquals(MDC.get(NamespaceLoggingContext.TAG_NAMESPACE_ID), NS);
    Assert.assertEquals(MDC.get(ApplicationLoggingContext.TAG_APPLICATION_ID), APP);
    Assert.assertEquals(MDC.get(GenericLoggingContext.TAG_ENTITY_ID), ENTITY);

    // Verify a different thread cannot change context
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        cancellable2.cancel();
      }

}