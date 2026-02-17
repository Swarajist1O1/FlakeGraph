class DummyClass_96959 {
  @Test
  public void testRoundTrip() throws Exception {
    Schema schema = Schema.create(Schema.Type.STRING);
    assertTrue(roundTrip(schema, "record", null) instanceof String);
    assertTrue(roundTrip(schema, "record", GenericData.class) instanceof Utf8);
  }

}