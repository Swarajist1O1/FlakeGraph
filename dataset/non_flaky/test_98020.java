class DummyClass_98020 {
  @Test
  public void testOptions() {
    UpdateOptions options = new UpdateOptions();

    WriteOption writeOption = ACKNOWLEDGED;
    assertEquals(options, options.setWriteOption(writeOption));
    assertEquals(writeOption, options.getWriteOption());

    boolean multi = TestUtils.randomBoolean();
    assertEquals(options, options.setMulti(multi));
    assertEquals(multi, options.isMulti());

    boolean upsert = TestUtils.randomBoolean();
    assertEquals(options, options.setUpsert(upsert));
    assertEquals(upsert, options.isUpsert());

    JsonArray arrayFilters = new JsonArray().add(new JsonObject().put(TestUtils.randomAlphaString(5), TestUtils.randomAlphaString(5)));
    assertEquals(options, options.setArrayFilters(arrayFilters));
    assertEquals(arrayFilters, options.getArrayFilters());
  }

}