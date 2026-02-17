class DummyClass_35664 {
  @Test
  public void testScalaSparkCrossNSDataset() throws Exception {
    // Deploy and create a dataset in namespace datasetSpaceForSpark
    NamespaceMeta inputDSNSMeta = new NamespaceMeta.Builder().setName("datasetSpaceForSpark").build();
    getNamespaceAdmin().create(inputDSNSMeta);
    deploy(inputDSNSMeta.getNamespaceId(), SparkAppUsingObjectStore.class);
    DataSetManager<ObjectStore<String>> keysManager = getDataset(inputDSNSMeta.getNamespaceId().dataset("keys"));
    prepareInputData(keysManager);

    Map<String, String> args = ImmutableMap.of(ScalaCharCountProgram.INPUT_DATASET_NAMESPACE(),
                                               inputDSNSMeta.getNamespaceId().getNamespace(),
                                               ScalaCharCountProgram.INPUT_DATASET_NAME(), "keys");

    ApplicationManager applicationManager = deploy(NamespaceId.DEFAULT, SparkAppUsingObjectStore.class);
    SparkManager sparkManager =
      applicationManager.getSparkManager(ScalaCharCountProgram.class.getSimpleName()).start(args);
    sparkManager.waitForRun(ProgramRunStatus.RUNNING, 10, TimeUnit.SECONDS);
    sparkManager.waitForStopped(60, TimeUnit.SECONDS);

    DataSetManager<KeyValueTable> countManager = getDataset("count");
    checkOutputData(countManager);
  }

}