class DummyClass_98070 {
  @Test
  public void testCopyOptions() {
    FindOptions options = new FindOptions();
    JsonObject fields = randomJsonObject();
    JsonObject sort = randomJsonObject();
    int limit = TestUtils.randomInt();
    int skip = TestUtils.randomInt();
    options.setFields(fields);
    options.setSort(sort);
    options.setLimit(limit);
    options.setSkip(skip);

    FindOptions copy = new FindOptions(options);
    assertEquals(options.getFields(), copy.getFields());
    assertEquals(options.getSort(), copy.getSort());
    assertEquals(options.getLimit(), copy.getLimit());
    assertEquals(options.getSkip(), copy.getSkip());
  }

}