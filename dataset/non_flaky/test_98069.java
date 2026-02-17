class DummyClass_98069 {
  @Test
  public void testDefaultOptionsJson() {
    FindOptions options = new FindOptions(new JsonObject());
    FindOptions def = new FindOptions();
    assertEquals(def.getFields(), options.getFields());
    assertEquals(def.getSort(), options.getSort());
    assertEquals(def.getLimit(), options.getLimit());
    assertEquals(def.getSkip(), options.getSkip());
  }

}