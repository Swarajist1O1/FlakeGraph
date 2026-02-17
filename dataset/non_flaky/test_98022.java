class DummyClass_98022 {
  @Test
  public void testOptionsJson() {
    JsonObject json = new JsonObject();

    WriteOption writeOption = JOURNALED;
    json.put("writeOption", writeOption.name());

    boolean multi = TestUtils.randomBoolean();
    json.put("multi", multi);

    boolean upsert = TestUtils.randomBoolean();
    json.put("upsert", upsert);

    JsonArray arrayFilters = new JsonArray().add(new JsonObject().put(TestUtils.randomAlphaString(5), TestUtils.randomAlphaString(5)));
    json.put("arrayFilters", arrayFilters);

    UpdateOptions options = new UpdateOptions(json);
    assertEquals(writeOption, options.getWriteOption());
    assertEquals(multi, options.isMulti());
    assertEquals(upsert, options.isUpsert());
    assertEquals(arrayFilters, options.getArrayFilters());
  }

}