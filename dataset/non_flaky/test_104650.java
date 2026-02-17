class DummyClass_104650 {
  @Test
  public void testCaseStatementWithInAggregation()
      throws Exception {
    testCountVsCaseQuery("origin = 'ATL'");
    testCountVsCaseQuery("origin <> 'ATL'");

    testCountVsCaseQuery("DaysSinceEpoch > 16312");
    testCountVsCaseQuery("DaysSinceEpoch >= 16312");
    testCountVsCaseQuery("DaysSinceEpoch < 16312");
    testCountVsCaseQuery("DaysSinceEpoch <= 16312");
    testCountVsCaseQuery("DaysSinceEpoch = 16312");
    testCountVsCaseQuery("DaysSinceEpoch <> 16312");
  }

}