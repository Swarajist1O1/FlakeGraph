class DummyClass_176887 {
  @Test
  public void testOverlayOn() {
    Map<String,Object> overlay = new HashMap<>();
    overlay.put("foo", "bar");
    Config config = ConfigUtils.overlayOn(overlay, ConfigUtils.getDefault());
    assertEquals("bar", config.getString("foo"));
  }

}