class DummyClass_98013 {
  @Test
  public void testFindReturnsStringId() throws Exception {
    String collection = randomCollection();
    mongoClient.createCollection(collection, onSuccess(res -> {
      JsonObject orig = createDoc();
      JsonObject doc = orig.copy();
      mongoClient.insert(collection, doc, onSuccess(id -> {
        assertNotNull(id);
        mongoClient.find(collection, new JsonObject().put("foo", "bar"), onSuccess(list -> {
          assertTrue(list.size() == 1);
          JsonObject obj = list.get(0);
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