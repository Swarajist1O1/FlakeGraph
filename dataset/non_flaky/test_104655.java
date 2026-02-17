class DummyClass_104655 {
  @Test
  public void testDistinctQuery()
      throws Exception {
    // by default 10 rows will be returned, so use high limit
    String pql = "SELECT DISTINCT(Carrier) FROM mytable LIMIT 1000000";
    String sql = "SELECT DISTINCT Carrier FROM mytable";
    testQuery(pql, Collections.singletonList(sql));
    pql = "SELECT DISTINCT Carrier FROM mytable LIMIT 1000000";
    testSqlQuery(pql, Collections.singletonList(sql));

    pql = "SELECT DISTINCT(Carrier, DestAirportID) FROM mytable LIMIT 1000000";
    sql = "SELECT DISTINCT Carrier, DestAirportID FROM mytable";
    testQuery(pql, Collections.singletonList(sql));
    pql = "SELECT DISTINCT Carrier, DestAirportID FROM mytable LIMIT 1000000";
    testSqlQuery(pql, Collections.singletonList(sql));

    pql = "SELECT DISTINCT(Carrier, DestAirportID, DestStateName) FROM mytable LIMIT 1000000";
    sql = "SELECT DISTINCT Carrier, DestAirportID, DestStateName FROM mytable";
    testQuery(pql, Collections.singletonList(sql));
    pql = "SELECT DISTINCT Carrier, DestAirportID, DestStateName FROM mytable LIMIT 1000000";
    testSqlQuery(pql, Collections.singletonList(sql));

    pql = "SELECT DISTINCT(Carrier, DestAirportID, DestCityName) FROM mytable LIMIT 1000000";
    sql = "SELECT DISTINCT Carrier, DestAirportID, DestCityName FROM mytable";
    testQuery(pql, Collections.singletonList(sql));
    pql = "SELECT DISTINCT Carrier, DestAirportID, DestCityName FROM mytable LIMIT 1000000";
    testSqlQuery(pql, Collections.singletonList(sql));
  }

}