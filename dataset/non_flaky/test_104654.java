class DummyClass_104654 {
  @Test
  public void testQueryWithAlias()
      throws Exception {
    {
      //test same alias name with column name
      String query =
          "SELECT ArrTime AS ArrTime, Carrier AS Carrier, DaysSinceEpoch AS DaysSinceEpoch FROM mytable ORDER BY "
              + "DaysSinceEpoch DESC";
      testSqlQuery(query, Collections.singletonList(query));

      query =
          "SELECT ArrTime AS ArrTime, DaysSinceEpoch AS DaysSinceEpoch, Carrier AS Carrier FROM mytable ORDER BY "
              + "Carrier DESC";
      testSqlQuery(query, Collections.singletonList(query));

      query =
          "SELECT ArrTime AS ArrTime, DaysSinceEpoch AS DaysSinceEpoch, Carrier AS Carrier FROM mytable ORDER BY "
              + "Carrier DESC, ArrTime DESC";
      testSqlQuery(query, Collections.singletonList(query));
    }
    {
      //test single alias
      String query = "SELECT ArrTime, Carrier AS CarrierName, DaysSinceEpoch FROM mytable ORDER BY DaysSinceEpoch DESC";
      testSqlQuery(query, Collections.singletonList(query));

      query = "SELECT count(*) AS cnt, max(ArrTime) as maxArrTime FROM mytable";
      testSqlQuery(query, Collections.singletonList(query));

      query = "SELECT count(*) AS cnt, Carrier AS CarrierName FROM mytable GROUP BY CarrierName ORDER BY cnt";
      testSqlQuery(query, Collections.singletonList(query));
    }
    {
      //test multiple alias
      String query =
          "SELECT ArrTime, Carrier, Carrier AS CarrierName1, Carrier AS CarrierName2, DaysSinceEpoch FROM mytable "
              + "ORDER BY DaysSinceEpoch DESC";
      testSqlQuery(query, Collections.singletonList(query));

      query = "SELECT count(*) AS cnt, max(ArrTime) as maxArrTime1, max(ArrTime) as maxArrTime2 FROM mytable";
      testSqlQuery(query, Collections.singletonList(query));

      query =
          "SELECT count(*), count(*) AS cnt1, count(*) AS cnt2, Carrier AS CarrierName FROM mytable GROUP BY "
              + "CarrierName ORDER BY cnt2";
      testSqlQuery(query, Collections.singletonList(query));
    }
  }

}