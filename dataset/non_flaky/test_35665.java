class DummyClass_35665 {
  @Test
  public void testSparkWithLocalFiles() throws Exception {
    testSparkWithLocalFiles(SparkAppUsingLocalFiles.class,
                            SparkAppUsingLocalFiles.JavaSparkUsingLocalFiles.class.getSimpleName(), "java");
    testSparkWithLocalFiles(SparkAppUsingLocalFiles.class,
                            SparkAppUsingLocalFiles.ScalaSparkUsingLocalFiles.class.getSimpleName(), "scala");
  }

}