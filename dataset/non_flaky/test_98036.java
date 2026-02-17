class DummyClass_98036 {
  @Test
  public void testToJsonMongoClientBulkWriteResult() {
    JsonObject mongoClientBulkWriteResultJson = randomMongoClientBulkWriteResultJson();
    MongoClientBulkWriteResult mongoClientBulkWriteResult = new MongoClientBulkWriteResult(
        mongoClientBulkWriteResultJson);

    assertEquals(mongoClientBulkWriteResultJson, mongoClientBulkWriteResult.toJson());
  }

}