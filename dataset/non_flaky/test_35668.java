class DummyClass_35668 {
  @Test
  public void testSQLQuery() throws Exception {
    getTestManager().deployDatasetModule(NamespaceId.DEFAULT.datasetModule("my-kv"), AppUsingCustomModule.Module.class);

    DatasetAdmin dsAdmin = getTestManager().addDatasetInstance("myKeyValueTable",
                                                               NamespaceId.DEFAULT.dataset("myTable"));
    Assert.assertTrue(dsAdmin.exists());

    ApplicationManager appManager = deployApplication(NamespaceId.DEFAULT, AppUsingCustomModule.class);
    ServiceManager serviceManager = appManager.getServiceManager("MyService").start();
    serviceManager.waitForRun(ProgramRunStatus.RUNNING, 10, TimeUnit.SECONDS);

    put(serviceManager, "a", "1");
    put(serviceManager, "b", "2");
    put(serviceManager, "c", "1");

    try (
      Connection connection = getTestManager().getQueryClient(NamespaceId.DEFAULT);
      // the value (character) "1" corresponds to the decimal 49. In hex, that is 31.
      ResultSet results = connection.prepareStatement("select key from dataset_mytable where hex(value) = '31'")
        .executeQuery()
    ) {
      // run a query over the dataset
      Assert.assertTrue(results.next());
      Assert.assertEquals("a", results.getString(1));
      Assert.assertTrue(results.next());
      Assert.assertEquals("c", results.getString(1));
      Assert.assertFalse(results.next());
    }

    dsAdmin.drop();
    Assert.assertFalse(dsAdmin.exists());
  }

}