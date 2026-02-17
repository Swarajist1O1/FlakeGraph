class DummyClass_98068 {
  @Test
  public void testOptionsJson() {
    JsonObject json = new JsonObject();

    JsonObject fields = randomJsonObject();
    json.put("fields", fields);

    JsonObject sort = randomJsonObject();
    json.put("sort", sort);

    int limit = TestUtils.randomInt();
    json.put("limit", limit);

    int skip = TestUtils.randomInt();
    json.put("skip", skip);

    FindOptions options = new FindOptions(json);
    assertEquals(fields, options.getFields());
    assertEquals(sort, options.getSort());
    assertEquals(limit, options.getLimit());
    assertEquals(skip, options.getSkip());
  }

}