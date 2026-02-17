class DummyClass_106573 {
  @Test
  public void ufsContractTest() throws Exception {
    File ufsPath = mFolder.newFolder("ufsContractTest");

    try {
      UnderFileSystemContractTest
          .main(new String[] {"--path", "file://" + ufsPath.getAbsolutePath()});
    } catch (Throwable e) {
      fail("UFS contract failed: " + e.getMessage());
    }
  }

}