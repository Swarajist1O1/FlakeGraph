class DummyClass_96086 {
  @Test
  public void testReady() {
    String result = IOUtils.slurpURLNoExceptions("http://localhost:" + port + "/ready");
    Assert.assertNotNull(result);
    Assert.assertEquals("ready", result.trim());
  }

}