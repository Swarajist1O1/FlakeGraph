class DummyClass_35666 {
  @Test
  public void testPySpark() throws Exception {
    if (TestBase.getCurrentSparkCompat() == SparkCompat.SPARK3_2_12) {
      //For spark 3 we need python 3, otherwise skip test
      try {
        Process python = new ProcessBuilder("python3", "-V").start();
        int resultCode = python.waitFor();
        assumeTrue("Python3 returned error, result code: " + resultCode,
                   resultCode == 0);
      } catch (IOException e) {
        assumeNoException("Python3 can't be started", e);
      }
    }
    ApplicationManager appManager = deploy(NamespaceId.DEFAULT, Spark2TestApp.class);

    // Write some data to a local file
    File inputFile = TEMP_FOLDER.newFile();
    try (BufferedWriter writer = Files.newBufferedWriter(inputFile.toPath(), StandardCharsets.UTF_8)) {
      for (int i = 0; i < 100; i++) {
        writer.write("Event " + i);
        writer.newLine();
      }
    }

    File outputDir = new File(TMP_FOLDER.newFolder(), "output");
    appManager.getSparkManager(PythonSpark2.class.getSimpleName()).startAndWaitForGoodRun(
      ImmutableMap.of("input.file", inputFile.getAbsolutePath(),
                      "output.path", outputDir.getAbsolutePath()),
      ProgramRunStatus.COMPLETED, 2, TimeUnit.MINUTES);

    // Verify the result
    File resultFile = DirUtils.listFiles(outputDir).stream()
      .filter(f -> !f.getName().endsWith(".crc"))
      .filter(f -> !f.getName().startsWith("_SUCCESS"))
      .findFirst()
      .orElse(null);
    Assert.assertNotNull(resultFile);

    List<String> lines = Files.readAllLines(resultFile.toPath(), StandardCharsets.UTF_8);
    Assert.assertFalse(lines.isEmpty());

    // Expected only even number
    int count = 0;
    for (String line : lines) {
      line = line.trim();
      if (!line.isEmpty()) {
        Assert.assertEquals("Event " + count, line);
        count += 2;
      }
    }

    Assert.assertEquals(100, count);

    final Map<String, String> tags = ImmutableMap.of(
      Constants.Metrics.Tag.NAMESPACE, NamespaceId.DEFAULT.getNamespace(),
      Constants.Metrics.Tag.APP, Spark2TestApp.class.getSimpleName(),
      Constants.Metrics.Tag.SPARK, PythonSpark2.class.getSimpleName());

    Tasks.waitFor(100L, () ->  getMetricsManager().getTotalMetric(tags, "user.body"),
                  5, TimeUnit.SECONDS, 100, TimeUnit.MILLISECONDS);
  }

}