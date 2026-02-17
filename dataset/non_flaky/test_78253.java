class DummyClass_78253 {
  @Test
  public void testWatermarkEarliestState() throws Exception {
    WatermarkHoldState value = underTest.state(NAMESPACE_1, WATERMARK_EARLIEST_ADDR);

    // State instances are cached, but depend on the namespace.
    assertEquals(value, underTest.state(NAMESPACE_1, WATERMARK_EARLIEST_ADDR));
    assertFalse(value.equals(underTest.state(NAMESPACE_2, WATERMARK_EARLIEST_ADDR)));

    assertThat(value.read(), Matchers.nullValue());
    value.add(new Instant(2000));
    assertThat(value.read(), equalTo(new Instant(2000)));

    value.add(new Instant(3000));
    assertThat(value.read(), equalTo(new Instant(2000)));

    value.add(new Instant(1000));
    assertThat(value.read(), equalTo(new Instant(1000)));

    value.clear();
    assertThat(value.read(), equalTo(null));
    assertThat(underTest.state(NAMESPACE_1, WATERMARK_EARLIEST_ADDR), equalTo(value));
  }

}