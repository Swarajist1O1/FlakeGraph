class DummyClass_98067 {
  @Test
  public void testDefaultOptions() {
    FindOptions options = new FindOptions();
    assertNotNull(options.getFields());
    assertTrue(options.getFields().isEmpty());
    assertNotNull(options.getSort());
    assertTrue(options.getSort().isEmpty());
    assertEquals(FindOptions.DEFAULT_LIMIT, options.getLimit());
    assertEquals(FindOptions.DEFAULT_SKIP, options.getSkip());
  }

}