class DummyClass_78256 {
  @Test
  public void testWatermarkStateIsEmpty() throws Exception {
    WatermarkHoldState value = underTest.state(NAMESPACE_1, WATERMARK_EARLIEST_ADDR);

    assertThat(value.isEmpty().read(), Matchers.is(true));
    ReadableState<Boolean> readFuture = value.isEmpty();
    value.add(new Instant(1000));
    assertThat(readFuture.read(), Matchers.is(false));

    value.clear();
    assertThat(readFuture.read(), Matchers.is(true));
  }

}