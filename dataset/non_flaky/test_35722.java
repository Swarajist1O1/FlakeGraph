class DummyClass_35722 {
  @Test
  public void testMDC() {
    LoggingContext context = new TestLoggingContext("namespace", "app", "run", "instance");

    // Put an entry in the user mdc. It shouldn't override what's in the system tags.
    Map<String, String> userMDC = new HashMap<>();
    userMDC.put(Constants.Logging.TAG_APPLICATION_ID, "userApp");

    Map<String, String> mdc = new LoggingContextMDC(context.getSystemTagsAsString(), userMDC);

    Assert.assertEquals(4, mdc.size());

    Map<String, String> copiedMDC = new HashMap<>();
    for (Map.Entry<String, String> entry : mdc.entrySet()) {
      copiedMDC.put(entry.getKey(), entry.getValue());
    }

    Assert.assertEquals(4, copiedMDC.size());
    Assert.assertEquals("namespace", copiedMDC.get(Constants.Logging.TAG_NAMESPACE_ID));
    Assert.assertEquals("app", copiedMDC.get(Constants.Logging.TAG_APPLICATION_ID));
    Assert.assertEquals("run", copiedMDC.get(Constants.Logging.TAG_RUN_ID));
    Assert.assertEquals("instance", copiedMDC.get(Constants.Logging.TAG_INSTANCE_ID));

    // Should be able to set user property
    mdc.put("user", "test");
    Assert.assertEquals(5, mdc.size());
    Assert.assertEquals(5, mdc.entrySet().size());

    // This should fail with exception
    try {
      mdc.put(Constants.Logging.TAG_APPLICATION_ID, "newApp");
      Assert.fail();
    } catch (IllegalArgumentException e) {
      // expected
    }
  }

}