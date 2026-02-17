class DummyClass_98026 {
  @Test
  public void testMongoClientUpdateResultStatuses() {
    long randomMatched = TestUtils.randomLong();
    JsonObject randomUpsertedId = randomUpsertId();
    long randomModified = TestUtils.randomLong();

    MongoClientUpdateResult mongoClientUpdateResult = new MongoClientUpdateResult(randomMatched, randomUpsertedId, randomModified);

    assertEquals(randomMatched, mongoClientUpdateResult.getDocMatched());
    assertEquals(randomUpsertedId, mongoClientUpdateResult.getDocUpsertedId());
    assertEquals(randomModified, mongoClientUpdateResult.getDocModified());
  }

}