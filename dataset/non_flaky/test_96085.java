class DummyClass_96085 {
  @Test
  public void testLive() {
    String result = IOUtils.slurpURLNoExceptions("http://localhost:" + port + "/live");
    Assert.assertNotNull(result);
    Assert.assertEquals("live", result.trim());
  }

}