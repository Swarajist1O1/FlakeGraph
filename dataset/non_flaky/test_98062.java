class DummyClass_98062 {
  @Test
  public void testAggregateUpdateCollection() {
    String collection = randomCollection();
    mongoClient.insert(collection, new JsonObject().put("price", 10).put("quantity", 1), onSuccess(id -> {
      mongoClient.insert(collection, new JsonObject().put("price", 20).put("quantity", 2), onSuccess(id2 -> {
        mongoClient.insert(collection, new JsonObject().put("price", 30).put("quantity", 10), onSuccess(id3 -> {
          mongoClient.updateCollection(collection,
            // reduce price of low quantity items
            new JsonObject().put("quantity", new JsonObject().put("$lte", 2)),
            new JsonArray().add(new JsonObject().put("$set", new JsonObject().put("price", new JsonObject().put("$subtract", new JsonArray().add("$price").add(2))))),
            onSuccess(res -> {
              assertEquals(2, res.getDocModified());
              assertEquals(2, res.getDocMatched());
              testComplete();
            }));
        }));
      }));
    }));
    await();
  }

}