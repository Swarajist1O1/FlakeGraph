class DummyClass_98011 {
  @Test
  public void testFindOneReturnsStringId() throws Exception {
    String collection = randomCollection();
    mongoClient.createCollection(collection, onSuccess(res -> {
      JsonObject orig = createDoc();
      JsonObject doc = orig.copy();
      mongoClient.insert(collection, doc, onSuccess(id -> {
        assertNotNull(id);
        mongoClient.findOne(collection, new JsonObject().put("foo", "bar"), null, onSuccess(obj -> {
          assertTrue(obj.containsKey("_id"));
          assertTrue(obj.getValue("_id") instanceof String);
          obj.remove("_id");
          assertEquals(orig, obj);
          testComplete();
        }));
      }));
    }));
    await();
  }

}