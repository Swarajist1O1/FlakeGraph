class DummyClass_98084 {
  @Test
  public void testConnStringNoWriteConcern() {
    final ConnectionString connString = new ConnectionString("mongodb://localhost:27017/mydb?replicaSet=myapp");
    WriteConcern rp = new WriteConcernParser(connString, new JsonObject()).writeConcern();
    assertNull(rp);
  }

}