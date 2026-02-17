class DummyClass_104652 {
  @Test
  public void testQueryWithRepeatedColumns()
      throws Exception {
    //test repeated columns in selection query
    String query = "SELECT ArrTime, ArrTime FROM mytable WHERE DaysSinceEpoch <= 16312 AND Carrier = 'DL'";
    testQuery(query, Collections.singletonList(query));

    //test repeated columns in selection query with order by
    query = "SELECT ArrTime, ArrTime FROM mytable WHERE DaysSinceEpoch <= 16312 AND Carrier = 'DL' order by ArrTime";
    testQuery(query, Collections.singletonList(query));

    //test repeated columns in agg query
    query = "SELECT count(*), count(*) FROM mytable WHERE DaysSinceEpoch <= 16312 AND Carrier = 'DL'";
    testQuery(query, Arrays.asList("SELECT count(*) FROM mytable WHERE DaysSinceEpoch <= 16312 AND Carrier = 'DL'",
        "SELECT count(*) FROM mytable WHERE DaysSinceEpoch <= 16312 AND Carrier = 'DL'"));

    //test repeated columns in agg group by query
    query =
        "SELECT ArrTime, ArrTime, count(*), count(*) FROM mytable WHERE DaysSinceEpoch <= 16312 AND Carrier = 'DL' "
            + "group by ArrTime, ArrTime";
    testQuery(query, Arrays.asList(
        "SELECT ArrTime, ArrTime, count(*) FROM mytable WHERE DaysSinceEpoch <= 16312 AND Carrier = 'DL' group by "
            + "ArrTime, ArrTime",
        "SELECT ArrTime, ArrTime, count(*) FROM mytable WHERE DaysSinceEpoch <= 16312 AND Carrier = 'DL' group by "
            + "ArrTime, ArrTime"));
  }

}