class DummyClass_98046 {
  @Test
  public void testSharedNamed() throws Exception {
    LocalMap<String, Object> map = getLocalMap();
    JsonObject config = getConfig();
    MongoClient client1 = MongoClient.createShared(vertx, config, "ds1");
    assertEquals(1, map.size());
    MongoClient client2 = MongoClient.createShared(vertx, config, "ds1");
    assertEquals(1, map.size());
    MongoClient client3 = MongoClient.createShared(vertx, config, "ds1");
    assertEquals(1, map.size());

    MongoClient client4 = MongoClient.createShared(vertx, config, "ds2");
    assertEquals(2, map.size());
    MongoClient client5 = MongoClient.createShared(vertx, config, "ds2");
    assertEquals(2, map.size());
    MongoClient client6 = MongoClient.createShared(vertx, config, "ds2");
    assertEquals(2, map.size());

    client1.close();
    assertEquals(2, map.size());
    client2.close();
    assertEquals(2, map.size());
    client3.close();
    assertEquals(1, map.size());

    client4.close();
    assertEquals(1, map.size());
    client5.close();
    assertEquals(1, map.size());
    client6.close();
    assertEquals(0, map.size());
    assertNotSame(map, getLocalMap());
  }

}