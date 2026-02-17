class DummyClass_176886 {
  @Test
  public void testOptionalStringList() {
    assertNull(ConfigUtils.getOptionalStringList(ConfigUtils.getDefault(), "nonexistent"));
  }

}