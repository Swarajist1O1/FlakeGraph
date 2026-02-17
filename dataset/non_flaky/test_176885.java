class DummyClass_176885 {
  @Test
  public void testOptionalString() {
    assertNull(ConfigUtils.getOptionalString(ConfigUtils.getDefault(), "nonexistent"));
  }

}