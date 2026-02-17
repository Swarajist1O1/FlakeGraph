class DummyClass_98034 {
  @Test
  public void testCopyMongoClientBulkWriteResult() {
    MongoClientBulkWriteResult mongoClientBulkWriteResultOrigin = new MongoClientBulkWriteResult(TestUtils.randomLong(),
        TestUtils.randomLong(), TestUtils.randomLong(), TestUtils.randomLong(), randomUpsertIds());

    MongoClientBulkWriteResult mongoClientBulkWriteResultCopy = new MongoClientBulkWriteResult(
        mongoClientBulkWriteResultOrigin);

    assertEquals(mongoClientBulkWriteResultCopy.getMatchedCount(), mongoClientBulkWriteResultOrigin.getMatchedCount());
    assertEquals(mongoClientBulkWriteResultCopy.getModifiedCount(),
        mongoClientBulkWriteResultOrigin.getModifiedCount());
    assertEquals(mongoClientBulkWriteResultCopy.getInsertedCount(),
        mongoClientBulkWriteResultOrigin.getInsertedCount());
    assertEquals(mongoClientBulkWriteResultCopy.getDeletedCount(), mongoClientBulkWriteResultOrigin.getDeletedCount());
    assertEquals(mongoClientBulkWriteResultCopy.getUpserts(), mongoClientBulkWriteResultOrigin.getUpserts());
  }

}