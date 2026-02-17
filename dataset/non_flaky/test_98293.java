class DummyClass_98293 {
  @Test
  public void testHandlesNanosecondPrecision() throws Exception {
    assertThat(dockerDateFormat.parse("2015-09-18T17:44:28.145855389Z"), equalTo(expected));
  }

}