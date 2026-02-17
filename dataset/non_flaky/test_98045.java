class DummyClass_98045 {
  @Test
  public void testSharedDefault() throws Exception {
    LocalMap<String, Object> map = getLocalMap();
    JsonObject config = getConfig();
    MongoClient client1 = MongoClient.createShared(vertx, config);
    assertEquals(1, map.size());
    MongoClient client2 = MongoClient.createShared(vertx, config);
    assertEquals(1, map.size());
    MongoClient client3 = MongoClient.createShared(vertx, config);
    assertEquals(1, map.size());
    client1.close();
    assertEquals(1, map.size());
    client2.close();
    assertEquals(1, map.size());
    client3.close();
    assertEquals(0, map.size());
    assertNotSame(map, getLocalMap());
  }

}