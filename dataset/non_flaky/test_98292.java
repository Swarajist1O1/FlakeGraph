class DummyClass_98292 {
  @Test
  public void testHandlesMillisecondPrecision() throws Exception {
    assertThat(dockerDateFormat.parse(millisecondDateString), equalTo(expected));
  }

}