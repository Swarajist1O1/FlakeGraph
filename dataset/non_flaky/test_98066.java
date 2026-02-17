class DummyClass_98066 {
  @Test
  public void testOptions() {
    FindOptions options = new FindOptions();

    JsonObject fields = randomJsonObject();
    assertEquals(options, options.setFields(fields));
    assertEquals(fields, options.getFields());

    JsonObject sort = randomJsonObject();
    assertEquals(options, options.setSort(sort));
    assertEquals(sort, options.getSort());

    int limit = TestUtils.randomInt();
    assertEquals(options, options.setLimit(limit));
    assertEquals(limit, options.getLimit());

    int skip = TestUtils.randomInt();
    assertEquals(options, options.setSkip(skip));
    assertEquals(skip, options.getSkip());
  }

}