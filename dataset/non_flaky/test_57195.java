class DummyClass_57195 {
  @Test
  public void testGetReconDbDir() throws Exception {

    String filePath = folder.getRoot().getAbsolutePath();
    OzoneConfiguration configuration = new OzoneConfiguration();
    configuration.set("TEST_DB_DIR", filePath);

    File file = new ReconUtils().getReconDbDir(configuration,
        "TEST_DB_DIR");
    Assert.assertEquals(filePath, file.getAbsolutePath());
  }

}