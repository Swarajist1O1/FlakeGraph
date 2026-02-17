class DummyClass_98301 {
  @Test
  public void testToString() throws Exception {
    final long timestamp = 1487357474682L;
    final String expectedJson = "{\"date\":1487357474}";
    final TestClass testClass = new TestClass(new Date(timestamp));

    final String json = OBJECT_MAPPER.writeValueAsString(testClass);
    assertThat(json, equalTo(expectedJson));
  }

}