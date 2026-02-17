class DummyClass_98030 {
  @Test
  public void testToJsonMongoClientUpdateResult() {
    JsonObject mongoClientUpdateResultJson = randomMongoClientUpdateResultJson();
    MongoClientUpdateResult mongoClientUpdateResult = new MongoClientUpdateResult(mongoClientUpdateResultJson);

    assertEquals(mongoClientUpdateResultJson, mongoClientUpdateResult.toJson());
  }

}