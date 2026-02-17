class DummyClass_98028 {
  @Test
  public void testCopyMongoClientUpdateResult() {
    MongoClientUpdateResult mongoClientUpdateResultOrigin = new MongoClientUpdateResult(TestUtils.randomLong(),
      randomUpsertId(), TestUtils.randomLong());
    MongoClientUpdateResult mongoClientUpdateResultCopy = new MongoClientUpdateResult(mongoClientUpdateResultOrigin);

    assertEquals(mongoClientUpdateResultOrigin.getDocMatched(), mongoClientUpdateResultCopy.getDocMatched());
    assertEquals(mongoClientUpdateResultOrigin.getDocUpsertedId(), mongoClientUpdateResultCopy.getDocUpsertedId());
    assertEquals(mongoClientUpdateResultOrigin.getDocModified(), mongoClientUpdateResultCopy.getDocModified());
  }

}