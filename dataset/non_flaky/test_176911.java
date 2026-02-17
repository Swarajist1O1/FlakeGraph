class DummyClass_176911 {
  @Test
  public void testKMeans() throws Exception {
    Path tempDir = getTempDir();
    Path dataDir = tempDir.resolve("data");
    Path modelDir = tempDir.resolve("model");

    Map<String,Object> overlayConfig = new HashMap<>();
    overlayConfig.put("oryx.batch.update-class", KMeansUpdate.class.getName());
    ConfigUtils.set(overlayConfig, "oryx.batch.storage.data-dir", dataDir);
    ConfigUtils.set(overlayConfig, "oryx.batch.storage.model-dir", modelDir);
    overlayConfig.put("oryx.batch.streaming.generation-interval-sec", GEN_INTERVAL_SEC);
    overlayConfig.put("oryx.batch.streaming.block-interval-sec", BLOCK_INTERVAL_SEC);
    overlayConfig.put("oryx.kmeans.hyperparams.k", NUM_CLUSTERS);
    overlayConfig.put("oryx.kmeans.iterations", 5);
    overlayConfig.put("oryx.input-schema.num-features", NUM_FEATURES);
    overlayConfig.put("oryx.input-schema.categorical-features", "[]");
    overlayConfig.put("oryx.kmeans.evaluation-strategy", EVALUATION_STRATEGY);

    Config config = ConfigUtils.overlayOn(overlayConfig, getConfig());

    startMessaging();

    List<Pair<String, String>> updates = startServerProduceConsumeTopics(
        config,
        new RandomKMeansDataGenerator(NUM_FEATURES),
        DATA_TO_WRITE,
        WRITE_INTERVAL_MSEC);

    List<Path> modelInstanceDirs = IOUtils.listFiles(modelDir, "*");

    int generations = modelInstanceDirs.size();
    checkIntervals(generations, DATA_TO_WRITE, WRITE_INTERVAL_MSEC, GEN_INTERVAL_SEC);

    for (Path modelInstanceDir : modelInstanceDirs) {
      Path modelFile = modelInstanceDir.resolve(MLUpdate.MODEL_FILE_NAME);
      assertTrue("Model file should exist: " + modelFile, Files.exists(modelFile));
      assertTrue("Model file should not be empty: " + modelFile, Files.size(modelFile) > 0);
      PMMLUtils.read(modelFile); // Shouldn't throw exception
    }

    InputSchema schema = new InputSchema(config);

    for (Pair<String,String> km : updates) {

      String type = km.getFirst();
      String value = km.getSecond();

      assertEquals("MODEL", type);

      PMML pmml = PMMLUtils.fromString(value);

      checkHeader(pmml.getHeader());

      checkDataDictionary(schema, pmml.getDataDictionary());

      Model rootModel = pmml.getModels().get(0);

      ClusteringModel clusteringModel = (ClusteringModel) rootModel;

      // Check if Basic hyperparameters match
      assertEquals(NUM_CLUSTERS, clusteringModel.getNumberOfClusters().intValue());
      assertEquals(NUM_CLUSTERS, clusteringModel.getClusters().size());
      assertEquals(NUM_FEATURES, clusteringModel.getClusteringFields().size());
      assertEquals(ComparisonMeasure.Kind.DISTANCE,
                   clusteringModel.getComparisonMeasure().getKind());
      assertEquals(NUM_FEATURES, clusteringModel.getClusters().get(0).getArray().getN().intValue());
      for (Cluster cluster : clusteringModel.getClusters()) {
        assertTrue(cluster.getSize() > 0);
      }
    }
  }

}