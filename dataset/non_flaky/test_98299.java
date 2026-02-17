class DummyClass_98299 {
  @Test
  public void testFromString() throws Exception {
    final String json = toJson("{\"date\": \"%s\"}");

    final TestClass value = OBJECT_MAPPER.readValue(json, TestClass.class);
    assertThat(value.getDate(), equalTo(referenceDateTime.toDate()));
  }

}