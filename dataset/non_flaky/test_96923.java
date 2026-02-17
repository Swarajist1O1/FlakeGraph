class DummyClass_96923 {
  @Test
  public void runTestsInOrder() throws Exception {
    String pathOut = OUTPUT_DIR.getRoot().getPath();
    testJob(pathOut);
    testProjection(pathOut);
  }

}