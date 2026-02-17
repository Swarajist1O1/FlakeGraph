class DummyClass_176883 {
  @Test
  public void testDefaultConfig() {
    Config config = ConfigUtils.getDefault();
    assertEquals("yarn-client", config.getString("oryx.batch.streaming.master"));
  }

}