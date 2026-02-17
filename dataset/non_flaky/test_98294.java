class DummyClass_98294 {
  @Test
  public void testHandlesNanosecondWithLessThanNineDigits() throws Exception {
    assertThat(dockerDateFormat.parse("2015-09-18T17:44:28.1458553Z"), equalTo(expected));
  }

}