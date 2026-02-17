class DummyClass_96013 {
  @Test
  public void testDcoref() throws Exception {
    Counter<String> results = getCorefResults(runCorefTest(true));

    Counter<String> lowResults = new ClassicCounter<>();
    Counter<String> highResults = new ClassicCounter<>();
    Counter<String> expectedResults = new ClassicCounter<>();

    setLowHighExpected(lowResults, highResults, expectedResults, MENTION_TP, 12400, 12410, 12405);
    setLowHighExpected(lowResults, highResults, expectedResults, MENTION_F1, 50.4, 50.45, 50.42);

    setLowHighExpected(lowResults, highResults, expectedResults, MUC_TP, 6245, 6255, 6250);
    setLowHighExpected(lowResults, highResults, expectedResults, MUC_F1, 60.65, 60.7, 60.66);

    setLowHighExpected(lowResults, highResults, expectedResults, BCUBED_TP, 12440, 12452.25, 12452.25);
    setLowHighExpected(lowResults, highResults, expectedResults, BCUBED_F1, 70.75, 70.85, 70.80);

    setLowHighExpected(lowResults, highResults, expectedResults, CEAFM_TP, 10915, 10930, 10920);
    setLowHighExpected(lowResults, highResults, expectedResults, CEAFM_F1, 59.4, 59.5, 59.42);

    setLowHighExpected(lowResults, highResults, expectedResults, CEAFE_TP, 3830, 3840, 3831.36);
    setLowHighExpected(lowResults, highResults, expectedResults, CEAFE_F1, 47.4, 47.5, 47.45);

    setLowHighExpected(lowResults, highResults, expectedResults, BLANC_F1, 75.35, 75.44, 75.38);

    setLowHighExpected(lowResults, highResults, expectedResults, CONLL_SCORE, 59.6, 59.7, 59.64);

    BenchmarkingHelper.benchmarkResults(results, lowResults, highResults, expectedResults);
  }

}