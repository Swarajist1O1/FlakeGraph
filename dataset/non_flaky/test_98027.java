class DummyClass_98027 {
  @Test
  public void testDefaultMongoClientUpdateResult() {
    MongoClientUpdateResult mongoClientUpdateResult = new MongoClientUpdateResult();

    assertEquals(MongoClientUpdateResult.DEFAULT_DOCMATCHED, mongoClientUpdateResult.getDocMatched());
    assertNull(mongoClientUpdateResult.getDocUpsertedId());
    assertEquals(MongoClientUpdateResult.DEFAULT_DOCMODIFIED, mongoClientUpdateResult.getDocModified());
  }

}