class DummyClass_98025 {
  @Test
  public void testToJson() {
    UpdateOptions options = new UpdateOptions();
    WriteOption writeOption = MAJORITY;
    boolean multi = TestUtils.randomBoolean();
    boolean upsert = TestUtils.randomBoolean();
    JsonArray arrayFilters = new JsonArray().add(new JsonObject().put(TestUtils.randomAlphaString(5), TestUtils.randomAlphaString(5)));

    options.setWriteOption(writeOption);
    options.setMulti(multi);
    options.setUpsert(upsert);
    options.setArrayFilters(arrayFilters);

    assertEquals(options, new UpdateOptions(options.toJson()));
  }

}