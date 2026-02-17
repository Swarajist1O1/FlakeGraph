class DummyClass_98015 {
  @Test
  public void testInsertPreexistingID() throws Exception {
    String collection = randomCollection();
    mongoClient.createCollection(collection, onSuccess(res -> {
      JsonObject doc = createDoc();
      //Changed to hex string as a random string will not be valid for useObjectId = true
      doc.put("_id", new ObjectId().toHexString());
      mongoClient.insert(collection, doc, onSuccess(id -> {
        assertNull(id);
        testComplete();
      }));
    }));
    await();
  }

}