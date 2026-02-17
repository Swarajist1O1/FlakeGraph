class DummyClass_176884 {
  @Test
  public void testSerialize() {
    String serialized = ConfigUtils.serialize(ConfigUtils.getDefault());
    assertTrue(serialized.contains("update-class"));
    Config deserialized = ConfigUtils.deserialize(serialized);
    assertEquals(
        ConfigUtils.getDefault().getString("oryx.serving.api.port"),
        deserialized.getString("oryx.serving.api.port"));
  }

}