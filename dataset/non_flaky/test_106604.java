class DummyClass_106604 {
  @Test
  public void notEnoughVCoreForAlluxioMaster() throws Exception {
    mConf.set(PropertyKey.INTEGRATION_MASTER_RESOURCE_MEM, "2048.00MB");
    mConf.set(PropertyKey.INTEGRATION_MASTER_RESOURCE_CPU, "4");
    int masterMemInMB = (int) (mConf.getBytes(
        PropertyKey.INTEGRATION_MASTER_RESOURCE_MEM) / Constants.MB);
    int masterVCores = mConf.getInt(PropertyKey.INTEGRATION_MASTER_RESOURCE_CPU);
    Resource resource = Resource.newInstance(masterMemInMB, 3);
    generateMaxAllocation(resource);
    mThrown.expect(RuntimeException.class);
    mThrown.expectMessage(ExceptionMessage.YARN_NOT_ENOUGH_RESOURCES.getMessage(
        "Alluxio Master", "virtual cores", masterVCores, resource.getVirtualCores()));
    Client client = new Client(mConf);
    client.run();
  }

}