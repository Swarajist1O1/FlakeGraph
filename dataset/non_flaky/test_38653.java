class DummyClass_38653 {
  @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "test exception")
  public void testNotifyErrors() throws Exception {
    testBatchSource.notifyError(new RuntimeException("test exception"));
    testBatchSource.readNext();
  }

}