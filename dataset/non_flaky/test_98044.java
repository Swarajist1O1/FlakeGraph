class DummyClass_98044 {
  @Test
  public void testNonShared() {
    LocalMap<String, Object> map = getLocalMap();
    JsonObject config = getConfig();
    MongoClient client1 = MongoClient.create(vertx, config);
    assertEquals(1, map.size());
    MongoClient client2 = MongoClient.create(vertx, config);
    assertEquals(2, map.size());
    MongoClient client3 = MongoClient.create(vertx, config);
    assertEquals(3, map.size());
    client1.close();
    assertEquals(2, map.size());
    client2.close();
    assertEquals(1, map.size());
    client3.close();
    assertWaitUntil(() -> map.size() == 0);
    assertWaitUntil(() -> getLocalMap().size() == 0);
    assertWaitUntil(() -> map != getLocalMap()); // Map has been closed
  }

}