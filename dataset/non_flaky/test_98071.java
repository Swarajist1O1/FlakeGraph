class DummyClass_98071 {
  @Test
  public void testToJson() {
    FindOptions options = new FindOptions();
    JsonObject fields = randomJsonObject();
    JsonObject sort = randomJsonObject();
    int limit = TestUtils.randomPositiveInt();
    int skip = TestUtils.randomPositiveInt();
    options.setFields(fields);
    options.setSort(sort);
    options.setLimit(limit);
    options.setSkip(skip);

    assertEquals(options, new FindOptions(options.toJson()));
  }

}