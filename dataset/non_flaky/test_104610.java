class DummyClass_104610 {
  @Test
  public void testQueries()
      throws Exception {
    String sql = "SELECT SUM(AirTime), SUM(ArrDelay) FROM mytable";
    testSqlQuery(sql, Collections.singletonList(sql));
    sql = "SELECT SUM(AirTime), DaysSinceEpoch FROM mytable GROUP BY DaysSinceEpoch ORDER BY SUM(AirTime) DESC";
    testSqlQuery(sql, Collections.singletonList(sql));
    sql = "SELECT Origin, SUM(ArrDelay) FROM mytable WHERE Carrier = 'AA' GROUP BY Origin ORDER BY Origin";
    testSqlQuery(sql, Collections.singletonList(sql));
  }

}