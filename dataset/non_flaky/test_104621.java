class DummyClass_104621 {
  @Test
  public void testDictionaryBasedQueries()
      throws Exception {

    // Dictionary columns
    // int
    testDictionaryBasedFunctions("NASDelay");

    // long
    testDictionaryBasedFunctions("AirlineID");

    // double
    testDictionaryBasedFunctions("ArrDelayMinutes");

    // float
    testDictionaryBasedFunctions("DepDelayMinutes");

    // Non Dictionary columns
    // int
    testDictionaryBasedFunctions("ActualElapsedTime");

    // double
    testDictionaryBasedFunctions("DepDelay");

    // float
    testDictionaryBasedFunctions("ArrDelay");
  }

}