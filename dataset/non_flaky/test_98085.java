class DummyClass_98085 {
  @Test
  public void testConnStringWriteConcern() {
    final ConnectionString connString = new ConnectionString("mongodb://localhost:27017/mydb?replicaSet=myapp&safe=true");
    WriteConcern wc = new WriteConcernParser(connString, new JsonObject()).writeConcern();

    assertNotNull(wc);
    assertEquals(WriteConcern.ACKNOWLEDGED, wc);
  }

}