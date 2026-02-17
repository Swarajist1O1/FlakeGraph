class DummyClass_98086 {
  @Test
  public void testConnStringSimpleAndAdvancedWriteConcern() {
    final ConnectionString connString = new ConnectionString("mongodb://localhost:27017/mydb?replicaSet=myapp" +
      "&w=majority&wtimeoutms=20&journal=false");
    WriteConcern expected = new WriteConcern("majority").withWTimeout(20, TimeUnit.MILLISECONDS).withJournal(false);
    WriteConcern wc = new WriteConcernParser(connString, new JsonObject()).writeConcern();
    assertNotNull(wc);
    assertEquals(expected, wc);
  }

}