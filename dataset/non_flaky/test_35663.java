class DummyClass_35663 {
  @Test
  public void testScalaSparkCrossNSStream() throws Exception {
    // create a namespace for input and create a file set instance
    NamespaceMeta inputNSMeta = new NamespaceMeta.Builder().setName("inputSpaceForSpark").build();
    getNamespaceAdmin().create(inputNSMeta);
    DatasetId inputDatasetId = inputNSMeta.getNamespaceId().dataset("input");
    addDatasetInstance(FileSet.class.getName(), inputDatasetId,
                       FileSetProperties.builder().setInputFormat(TextInputFormat.class).build());

    // create a namespace for dataset and add the dataset instance in it
    NamespaceMeta outputNSMeta = new NamespaceMeta.Builder().setName("crossNSDataset").build();
    getNamespaceAdmin().create(outputNSMeta);
    addDatasetInstance(outputNSMeta.getNamespaceId().dataset("count"), "keyValueTable");

    // write something to the input dataset
    Location inputFile = this.<FileSet>getDataset(inputDatasetId).get().getLocation("inputFile");
    try (PrintStream printer = new PrintStream(inputFile.getOutputStream(), true, "UTF-8")) {
      for (int i = 0; i < 50; i++) {
        printer.println(String.valueOf(i));
      }
    }

    // deploy the spark app in another namespace (default)
    ApplicationManager applicationManager = deploy(NamespaceId.DEFAULT, SparkAppUsingObjectStore.class);

    Map<String, String> args = new HashMap<>();
    args.put(ScalaCrossNSProgram.INPUT_NAMESPACE(), inputNSMeta.getNamespaceId().getNamespace());
    args.put(ScalaCrossNSProgram.OUTPUT_NAMESPACE(), outputNSMeta.getNamespaceId().getNamespace());
    args.put(ScalaCrossNSProgram.OUTPUT_NAME(), "count");

    FileSetArguments.setInputPath(args, "inputFile");

    SparkManager sparkManager =
      applicationManager.getSparkManager(ScalaCrossNSProgram.class.getSimpleName()).start(args);
    sparkManager.waitForRun(ProgramRunStatus.RUNNING, 10, TimeUnit.SECONDS);
    sparkManager.waitForStopped(60, TimeUnit.SECONDS);

    // get the dataset from the other namespace where we expect it to exist and compare the data
    DataSetManager<KeyValueTable> countManager = getDataset(outputNSMeta.getNamespaceId().dataset("count"));
    KeyValueTable results = countManager.get();
    for (int i = 0; i < 50; i++) {
      byte[] key = String.valueOf(i).getBytes(Charsets.UTF_8);
      Assert.assertArrayEquals(key, results.read(key));
    }
  }

}