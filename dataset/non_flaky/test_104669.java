class DummyClass_104669 {
  @Test
  public void testPredicateOnMetrics()
      throws Exception {
    String starQuery;

    // Query containing predicate on one metric only
    starQuery = "SELECT SUM(DepDelayMinutes) FROM myStarTable WHERE DepDelay > 0";
    testStarQuery(starQuery);
    starQuery = "SELECT SUM(DepDelayMinutes) FROM myStarTable WHERE DepDelay BETWEEN 0 and 10000";
    testStarQuery(starQuery);

    // Query containing predicate on multiple metrics
    starQuery = "SELECT SUM(DepDelayMinutes) FROM myStarTable WHERE DepDelay > 0 AND ArrDelay > 0";
    testStarQuery(starQuery);

    // Query containing predicate on multiple metrics and dimensions
    starQuery =
        "SELECT SUM(DepDelayMinutes) FROM myStarTable WHERE DepDelay > 0 AND ArrDelay > 0 AND OriginStateName = "
            + "'Massachusetts'";
    testStarQuery(starQuery);
  }

}