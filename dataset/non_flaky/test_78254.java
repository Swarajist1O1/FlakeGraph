class DummyClass_78254 {
  @Test
  public void testWatermarkLatestState() throws Exception {
    WatermarkHoldState value = underTest.state(NAMESPACE_1, WATERMARK_LATEST_ADDR);

    // State instances are cached, but depend on the namespace.
    assertEquals(value, underTest.state(NAMESPACE_1, WATERMARK_LATEST_ADDR));
    assertFalse(value.equals(underTest.state(NAMESPACE_2, WATERMARK_LATEST_ADDR)));

    assertThat(value.read(), Matchers.nullValue());
    value.add(new Instant(2000));
    assertThat(value.read(), equalTo(new Instant(2000)));

    value.add(new Instant(3000));
    assertThat(value.read(), equalTo(new Instant(3000)));

    value.add(new Instant(1000));
    assertThat(value.read(), equalTo(new Instant(3000)));

    value.clear();
    assertThat(value.read(), equalTo(null));
    assertThat(underTest.state(NAMESPACE_1, WATERMARK_LATEST_ADDR), equalTo(value));
  }

}