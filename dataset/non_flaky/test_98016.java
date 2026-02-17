class DummyClass_98016 {
  @Test
  public void testInsertRetrieve() throws Exception {
    String collection = randomCollection();
    mongoClient.createCollection(collection, onSuccess(res -> {
      JsonObject doc = createDoc();
      doc.put("_id", new ObjectId().toHexString());
      mongoClient.insert(collection, doc, onSuccess(id -> {
        assertNull(id);
        mongoClient.findOne(collection, new JsonObject(), null, onSuccess(retrieved -> {
          assertEquals(doc, retrieved);
          testComplete();
        }));
      }));
    }));
    await();
  }

}