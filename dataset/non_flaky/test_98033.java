class DummyClass_98033 {
  @Test
  public void testDefaultMongoClientBulkWriteResult() {
    MongoClientBulkWriteResult mongoClientBulkWriteResult = new MongoClientBulkWriteResult();

    assertEquals(MongoClientBulkWriteResult.DEFAULT_MATCHED_COUNT, mongoClientBulkWriteResult.getMatchedCount());
    assertEquals(MongoClientBulkWriteResult.DEFAULT_MODIFIED_COUNT, mongoClientBulkWriteResult.getModifiedCount());
    assertEquals(MongoClientBulkWriteResult.DEFAULT_INSERTED_COUNT, mongoClientBulkWriteResult.getInsertedCount());
    assertEquals(MongoClientBulkWriteResult.DEFAULT_DELETED_COUNT, mongoClientBulkWriteResult.getDeletedCount());
    assertNull(mongoClientBulkWriteResult.getUpserts());
  }

}