class DummyClass_176920 {
  @Test
  public void testRDF() throws Exception {
    Path tempDir = getTempDir();
    Path dataDir = tempDir.resolve("data");
    Path modelDir = tempDir.resolve("model");

    Map<String,Object> overlayConfig = new HashMap<>();
    overlayConfig.put("oryx.batch.update-class", RDFUpdate.class.getName());
    ConfigUtils.set(overlayConfig, "oryx.batch.storage.data-dir", dataDir);
    ConfigUtils.set(overlayConfig, "oryx.batch.storage.model-dir", modelDir);
    overlayConfig.put("oryx.batch.streaming.generation-interval-sec", GEN_INTERVAL_SEC);
    overlayConfig.put("oryx.batch.streaming.block-interval-sec", BLOCK_INTERVAL_SEC);
    overlayConfig.put("oryx.rdf.num-trees", 10);
    overlayConfig.put("oryx.rdf.hyperparams.max-depth", MAX_DEPTH);
    // Low values like 1 are deliberately bad, won't work
    overlayConfig.put("oryx.rdf.hyperparams.max-depth", "[1," + MAX_DEPTH + "]");
    overlayConfig.put("oryx.rdf.hyperparams.max-split-candidates", MAX_SPLIT_CANDIDATES);
    overlayConfig.put("oryx.input-schema.num-features", 5);
    overlayConfig.put("oryx.input-schema.categorical-features", "[\"4\"]");
    overlayConfig.put("oryx.input-schema.id-features", "[\"0\"]");
    overlayConfig.put("oryx.input-schema.target-feature", "\"4\"");
    overlayConfig.put("oryx.ml.eval.candidates", 3);
    overlayConfig.put("oryx.ml.eval.parallelism", 2);
    Config config = ConfigUtils.overlayOn(overlayConfig, getConfig());

    startMessaging();

    startServerProduceConsumeTopics(
        config,
        new RandomCategoricalRDFDataGenerator(3),
        DATA_TO_WRITE,
        WRITE_INTERVAL_MSEC);

    List<Path> modelInstanceDirs = IOUtils.listFiles(modelDir, "*");

    checkIntervals(modelInstanceDirs.size(), DATA_TO_WRITE, WRITE_INTERVAL_MSEC, GEN_INTERVAL_SEC);

    Path latestModelDir = modelInstanceDirs.get(modelInstanceDirs.size() - 1);
    Path modelFile = latestModelDir.resolve(MLUpdate.MODEL_FILE_NAME);
    assertTrue("No such model file: " + modelFile, Files.exists(modelFile));

    PMML pmml = PMMLUtils.read(modelFile);

    assertEquals(3, pmml.getExtensions().size());
    Map<String,Object> expected = new HashMap<>();
    expected.put("maxSplitCandidates", MAX_SPLIT_CANDIDATES);
    expected.put("maxDepth", MAX_DEPTH);
    expected.put("impurity", IMPURITY);
    checkExtensions(pmml, expected);

    Pair<DecisionForest,CategoricalValueEncodings> forestEncoding = RDFPMMLUtils.read(pmml);
    DecisionForest forest = forestEncoding.getFirst();
    CategoricalValueEncodings encoding = forestEncoding.getSecond();
    Map<String,Integer> targetEncoding = encoding.getValueEncodingMap(4);

    int[] zeroOne = { 0, 1 };
    for (int f1 : zeroOne) {
      for (int f2 : zeroOne) {
        for (int f3 : zeroOne) {
          CategoricalPrediction prediction =
              (CategoricalPrediction) forest.predict(new Example(null,
                                                                 null,
                                                                 NumericFeature.forValue(f1),
                                                                 NumericFeature.forValue(f2),
                                                                 NumericFeature.forValue(f3)));
          boolean expectedPositive = f1 == 1 && f2 == 1 && f3 == 1;
          assertEquals(targetEncoding.get(Boolean.toString(expectedPositive)).intValue(),
                       prediction.getMostProbableCategoryEncoding());
        }
      }
    }

  }

}