class DummyClass_98023 {
  @Test
  public void testDefaultOptionsJson() {
    UpdateOptions options = new UpdateOptions(new JsonObject());
    UpdateOptions def = new UpdateOptions();
    assertEquals(def.getWriteOption(), options.getWriteOption());
    assertEquals(def.isMulti(), options.isMulti());
    assertEquals(def.isUpsert(), options.isUpsert());
    assertEquals(def.getArrayFilters(), options.getArrayFilters());
  }

}