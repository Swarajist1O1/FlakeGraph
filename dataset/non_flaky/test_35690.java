class DummyClass_35690 {
  @Test
  public void testEffectiveLevel() throws Exception {
    LoggerContext context = new LoggerContext();
    JoranConfigurator configurator = new JoranConfigurator();
    configurator.setContext(context);
    configurator.doConfigure(new InputSource(new StringReader(generateLogback("WARN", ImmutableMap.of(
      "test", "INFO",
      "test.a", "ERROR",
      "test.a.X", "DEBUG",
      "test.a.X$1", "OFF"
    )))));

    Assert.assertSame(context.getLogger("test"), Loggers.getEffectiveLogger(context, "test"));
    Assert.assertSame(context.getLogger("test.a"), Loggers.getEffectiveLogger(context, "test.a"));
    Assert.assertSame(context.getLogger("test.a.X"), Loggers.getEffectiveLogger(context, "test.a.X"));
    Assert.assertSame(context.getLogger("test.a.X$1"), Loggers.getEffectiveLogger(context, "test.a.X$1"));

    Assert.assertSame(context.getLogger(Logger.ROOT_LOGGER_NAME),
                      Loggers.getEffectiveLogger(context, "defaultToRoot"));
    Assert.assertSame(context.getLogger("test"),
                      Loggers.getEffectiveLogger(context, "test.defaultToTest"));
    Assert.assertSame(context.getLogger("test.a"),
                      Loggers.getEffectiveLogger(context, "test.a.defaultToTestDotA"));
    Assert.assertSame(context.getLogger("test.a.X"),
                      Loggers.getEffectiveLogger(context, "test.a.X.defaultToTestDotADotX"));
  }

}