class DummyClass_78255 {
  @Test
  public void testWatermarkEndOfWindowState() throws Exception {
    WatermarkHoldState value = underTest.state(NAMESPACE_1, WATERMARK_EOW_ADDR);

    // State instances are cached, but depend on the namespace.
    assertEquals(value, underTest.state(NAMESPACE_1, WATERMARK_EOW_ADDR));
    assertFalse(value.equals(underTest.state(NAMESPACE_2, WATERMARK_EOW_ADDR)));

    assertThat(value.read(), Matchers.nullValue());
    value.add(new Instant(2000));
    assertThat(value.read(), equalTo(new Instant(2000)));

    value.clear();
    assertThat(value.read(), equalTo(null));
    assertThat(underTest.state(NAMESPACE_1, WATERMARK_EOW_ADDR), equalTo(value));
  }

}