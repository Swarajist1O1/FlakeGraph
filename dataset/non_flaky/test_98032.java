class DummyClass_98032 {
  @Test
  public void testMongoClientBulkWriteStatuses() {
    long randomMatched = TestUtils.randomLong();
    long randomModified = TestUtils.randomLong();
    long randomInserted = TestUtils.randomLong();
    long randomDeleted = TestUtils.randomLong();
    List<JsonObject> upserts = randomUpsertIds();

    MongoClientBulkWriteResult mongoClientBulkWriteResult = new MongoClientBulkWriteResult(randomInserted,
        randomMatched, randomDeleted, randomModified, upserts);

    assertEquals(randomMatched, mongoClientBulkWriteResult.getMatchedCount());
    assertEquals(randomModified, mongoClientBulkWriteResult.getModifiedCount());
    assertEquals(randomInserted, mongoClientBulkWriteResult.getInsertedCount());
    assertEquals(randomDeleted, mongoClientBulkWriteResult.getDeletedCount());
    assertEquals(upserts, mongoClientBulkWriteResult.getUpserts());
  }

}