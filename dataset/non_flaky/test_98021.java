class DummyClass_98021 {
  @Test
  public void testDefaultOptions() {
    UpdateOptions options = new UpdateOptions();
    assertNull(options.getWriteOption());
    assertFalse(options.isMulti());
    assertFalse(options.isUpsert());
    assertNull(options.getArrayFilters());
  }

}