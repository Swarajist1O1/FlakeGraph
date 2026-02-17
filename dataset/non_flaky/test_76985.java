class DummyClass_76985 {
  @Test
  public void testMemoryRelease() throws Exception {
    String fileName = generateTextFile(10000, 10000);
    SparkConf sparkConf = createSparkConf();
    updateSparkConfWithRss(sparkConf);
    sparkConf.set("spark.executor.memory", "500m");
    updateRssStorage(sparkConf);

    // oom if there has no memory release
    runSparkApp(sparkConf, fileName);
  }

}