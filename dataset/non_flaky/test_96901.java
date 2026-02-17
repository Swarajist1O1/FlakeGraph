class DummyClass_96901 {
  @Test
  public void runTests() throws Exception {
    if (! "run".equals(TEST_MODE)) return;

    int passed = 0, failed = 0;

    for (GenTest t : tests) {
      try {
        t.run();
        passed++;
      } catch (Exception e) {
        failed++;
        System.err.println("Failed: " + t.testName());
        e.printStackTrace(System.err);
      }
    }

    if (failed > 0) {
      fail(String.valueOf(failed) + " tests failed");
    }
  }

}