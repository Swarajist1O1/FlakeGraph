class DummyClass_98024 {
  @Test
  public void testCopyOptions() {
    UpdateOptions options = new UpdateOptions();
    WriteOption writeOption = REPLICA_ACKNOWLEDGED;
    boolean multi = TestUtils.randomBoolean();
    boolean upsert = TestUtils.randomBoolean();
    JsonArray arrayFilters = new JsonArray().add(new JsonObject().put(TestUtils.randomAlphaString(5), TestUtils.randomAlphaString(5)));

    options.setWriteOption(writeOption);
    options.setMulti(multi);
    options.setUpsert(upsert);
    options.setArrayFilters(arrayFilters);

    UpdateOptions copy = new UpdateOptions(options);
    assertEquals(options.getWriteOption(), copy.getWriteOption());
    assertEquals(options.isMulti(), copy.isMulti());
    assertEquals(options.isUpsert(), copy.isUpsert());
    assertEquals(options.getArrayFilters(), copy.getArrayFilters());
  }

}