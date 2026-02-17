class DummyClass_98012 {
  @Test
  public void testFindOneReturnsNothing() throws Exception {
    String collection = randomCollection();
    mongoClient.createCollection(collection, onSuccess(res -> {
      JsonObject orig = createDoc();
      JsonObject doc = orig.copy();
      mongoClient.insert(collection, doc, onSuccess(id -> {
        assertNotNull(id);
        mongoClient.findOne(collection, new JsonObject().put("nothing", "xxrandomxx"), null, onSuccess(obj -> {
          assertNull(obj);
          testComplete();
        }));
      }));
    }));
    await();
  }

}