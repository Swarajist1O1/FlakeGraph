class DummyClass_104656 {
  @Test
  public void testNonAggregationGroupByQuery()
      throws Exception {
    // by default 10 rows will be returned, so use high limit
    String pql = "SELECT Carrier FROM mytable GROUP BY Carrier LIMIT 1000000";
    String sql = "SELECT Carrier FROM mytable GROUP BY Carrier";
    testSqlQuery(pql, Collections.singletonList(sql));

    pql = "SELECT Carrier, DestAirportID FROM mytable GROUP BY Carrier, DestAirportID LIMIT 1000000";
    sql = "SELECT Carrier, DestAirportID FROM mytable GROUP BY Carrier, DestAirportID";
    testSqlQuery(pql, Collections.singletonList(sql));

    pql =
        "SELECT Carrier, DestAirportID, DestStateName FROM mytable GROUP BY Carrier, DestAirportID, DestStateName "
            + "LIMIT 1000000";
    sql = "SELECT Carrier, DestAirportID, DestStateName FROM mytable GROUP BY Carrier, DestAirportID, DestStateName";
    testSqlQuery(pql, Collections.singletonList(sql));

    pql =
        "SELECT Carrier, DestAirportID, DestCityName FROM mytable GROUP BY Carrier, DestAirportID, DestCityName LIMIT"
            + " 1000000";
    sql = "SELECT Carrier, DestAirportID, DestCityName FROM mytable GROUP BY Carrier, DestAirportID, DestCityName";
    testSqlQuery(pql, Collections.singletonList(sql));

    pql = "SELECT ArrTime-DepTime FROM mytable GROUP BY ArrTime, DepTime LIMIT 1000000";
    sql = "SELECT ArrTime-DepTime FROM mytable GROUP BY ArrTime, DepTime";
    testSqlQuery(pql, Collections.singletonList(sql));

    pql = "SELECT ArrTime-DepTime,ArrTime/3,DepTime*2 FROM mytable GROUP BY ArrTime, DepTime LIMIT 1000000";
    sql = "SELECT ArrTime-DepTime,ArrTime/3,DepTime*2 FROM mytable GROUP BY ArrTime, DepTime";
    testSqlQuery(pql, Collections.singletonList(sql));

    pql = "SELECT ArrTime+DepTime FROM mytable GROUP BY ArrTime + DepTime LIMIT 1000000";
    sql = "SELECT ArrTime+DepTime FROM mytable GROUP BY ArrTime + DepTime";
    testSqlQuery(pql, Collections.singletonList(sql));
  }

}