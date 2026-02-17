class DummyClass_176908 {
  @Test
  public void testHyperParameterTuning() throws Exception {
    Path tempDir = getTempDir();
    Path dataDir =  tempDir.resolve("data");
    Path modelDir = tempDir.resolve("model");

    Map<String,Object> overlayConfig = new HashMap<>();
    overlayConfig.put("oryx.batch.update-class", ALSUpdate.class.getName());
    ConfigUtils.set(overlayConfig, "oryx.batch.storage.data-dir", dataDir);
    ConfigUtils.set(overlayConfig, "oryx.batch.storage.model-dir", modelDir);
    overlayConfig.put("oryx.batch.streaming.generation-interval-sec", GEN_INTERVAL_SEC);
    overlayConfig.put("oryx.batch.streaming.block-interval-sec", BLOCK_INTERVAL_SEC);
    // Choose pairs of values where the best is predictable
    overlayConfig.put("oryx.als.hyperparams.features", "[1," + TEST_FEATURES + "]");
    overlayConfig.put("oryx.ml.eval.candidates", 2);
    overlayConfig.put("oryx.ml.eval.parallelism", 2);
    Config config = ConfigUtils.overlayOn(overlayConfig, getConfig());

    startMessaging();

    startServerProduceConsumeTopics(config,
                                    new FeaturesALSDataGenerator(TEST_ELEMENTS,
                                                                 TEST_ELEMENTS,
                                                                 TEST_FEATURES),
                                    DATA_TO_WRITE,
                                    WRITE_INTERVAL_MSEC);

    List<Path> modelInstanceDirs = IOUtils.listFiles(modelDir, "*");

    checkIntervals(modelInstanceDirs.size(), DATA_TO_WRITE, WRITE_INTERVAL_MSEC, GEN_INTERVAL_SEC);

    Path latestModelDir = modelInstanceDirs.get(modelInstanceDirs.size() - 1);
    Path modelFile = latestModelDir.resolve(MLUpdate.MODEL_FILE_NAME);
    assertTrue("No such model file: " + modelFile, Files.exists(modelFile));

    PMML pmml = PMMLUtils.read(modelFile);
    assertEquals(8, pmml.getExtensions().size());
    assertNotNull(AppPMMLUtils.getExtensionValue(pmml, "X"));
    assertNotNull(AppPMMLUtils.getExtensionValue(pmml, "Y"));
    Map<String,Object> expected = new HashMap<>();
    expected.put("features", TEST_FEATURES);
    expected.put("lambda", 0.001);
    expected.put("implicit", true);
    expected.put("alpha", 1.0);
    checkExtensions(pmml, expected);
  }

}