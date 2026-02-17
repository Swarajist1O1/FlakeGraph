class DummyClass_104660 {
  @Test
  public void testQuerySourceWithDatabaseName()
      throws Exception {
    // by default 10 rows will be returned, so use high limit
    String pql = "SELECT DISTINCT(Carrier) FROM mytable LIMIT 1000000";
    String sql = "SELECT DISTINCT Carrier FROM mytable";
    testQuery(pql, Collections.singletonList(sql));
    pql = "SELECT DISTINCT Carrier FROM db.mytable LIMIT 1000000";
    testSqlQuery(pql, Collections.singletonList(sql));
  }

}