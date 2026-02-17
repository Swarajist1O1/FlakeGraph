class DummyClass_98018 {
  @Test
  public void testInsertAlreadyExists() throws Exception {
    String collection = randomCollection();
    mongoClient.createCollection(collection, onSuccess(res -> {
      JsonObject doc = createDoc();
      mongoClient.insert(collection, doc, onSuccess(id -> {
        assertNotNull(id);
        doc.put("_id", id);
        mongoClient.insert(collection, doc, response -> {
          assertFalse(response.succeeded());
          testComplete();
        });
      }));
    }));
    await();
  }

}