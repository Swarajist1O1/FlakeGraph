class DummyClass_98017 {
  @Test
  public void testSavePreexistingObjectID() throws Exception {
    String collection = randomCollection();
    mongoClient.createCollection(collection, onSuccess(res -> {
      JsonObject doc = createDoc();
      //Changed to hex string as a random string will not be valid for useObjectId = true
      doc.put("_id", new ObjectId().toHexString());
      mongoClient.saveWithOptions(collection, doc, ACKNOWLEDGED, onSuccess(id -> {
        assertNull(id);
        testComplete();
      }));
    }));
    await();
  }

}