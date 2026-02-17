class DummyClass_96927 {
  @Test
  public void runTestsInOrder() throws Exception {
    String avroPath = OUTPUT_DIR.getRoot().getPath();
    testJob(avroPath);
    testProjection(avroPath);
    testProjectionNewMethodsOne(avroPath);
    testProjectionNewMethodsTwo(avroPath);
    testProjection1(avroPath);
    testJobNoreducer();
    testProjectionNoreducer(avroPath);
  }

}