class DummyClass_35662 {
  @Test
  public void testScalaSparkWithObjectStore() throws Exception {
    ApplicationManager applicationManager = deploy(NamespaceId.DEFAULT, SparkAppUsingObjectStore.class);

    DataSetManager<ObjectStore<String>> keysManager = getDataset("keys");
    prepareInputData(keysManager);

    SparkManager sparkManager = applicationManager.getSparkManager(ScalaCharCountProgram.class.getSimpleName()).start();
    sparkManager.waitForRun(ProgramRunStatus.RUNNING, 10, TimeUnit.SECONDS);
    sparkManager.waitForStopped(60, TimeUnit.SECONDS);

    DataSetManager<KeyValueTable> countManager = getDataset("count");
    checkOutputData(countManager);
  }

}