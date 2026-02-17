class DummyClass_98019 {
  @Test
  public void testReplaceUpsert() {
    String collection = randomCollection();
    JsonObject doc = createDoc();
    mongoClient.insert(collection, doc, onSuccess(id -> {
      assertNotNull(id);
      JsonObject replacement = createDoc();
      replacement.put("replacement", true);
      mongoClient.replaceDocumentsWithOptions(collection, new JsonObject().put("_id", new ObjectId().toHexString()), replacement, new UpdateOptions(true), onSuccess(v -> {
        mongoClient.find(collection, new JsonObject(), onSuccess(list -> {
          assertNotNull(list);
          assertEquals(2, list.size());
          JsonObject result = null;
          for (JsonObject o : list) {
            if (o.containsKey("replacement")) {
              result = o;
            }
          }
          assertNotNull(result);
          testComplete();
        }));
      }));
    }));

    await();
  }

}