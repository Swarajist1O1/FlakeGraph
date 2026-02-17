class DummyClass_104653 {
  @Test
  public void testQueryWithOrderby()
      throws Exception {
    //test repeated columns in selection query
    String query = "SELECT ArrTime, Carrier, DaysSinceEpoch FROM mytable ORDER BY DaysSinceEpoch DESC";
    testQuery(query, Collections.singletonList(query));

    //test repeated columns in selection query
    query = "SELECT ArrTime, DaysSinceEpoch, Carrier FROM mytable ORDER BY Carrier DESC";
    testQuery(query, Collections.singletonList(query));

    //test repeated columns in selection query
    query = "SELECT ArrTime, DaysSinceEpoch, Carrier FROM mytable ORDER BY Carrier DESC, ArrTime DESC";
    testQuery(query, Collections.singletonList(query));
  }

}