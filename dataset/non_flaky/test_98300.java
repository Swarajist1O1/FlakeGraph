class DummyClass_98300 {
  @Test
  public void testFromNumber() throws Exception {
    final String json = toJson("{\"date\": %s}");

    final TestClass value = OBJECT_MAPPER.readValue(json, TestClass.class);
    assertThat(value.getDate(), equalTo(referenceDateTime.toDate()));
  }

}