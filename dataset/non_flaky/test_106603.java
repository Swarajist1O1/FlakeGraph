class DummyClass_106603 {
  @Test
  public void notEnoughMemoryForAlluxioMaster() throws Exception {
    mConf.set(PropertyKey.INTEGRATION_MASTER_RESOURCE_MEM, "2048.00MB");
    mConf.set(PropertyKey.INTEGRATION_MASTER_RESOURCE_CPU, "4");
    int masterMemInMB = (int) (mConf.getBytes(
        PropertyKey.INTEGRATION_MASTER_RESOURCE_MEM) / Constants.MB);
    Resource resource = Resource.newInstance(masterMemInMB / 2, 4);
    generateMaxAllocation(resource);
    mThrown.expect(RuntimeException.class);
    mThrown.expectMessage(ExceptionMessage.YARN_NOT_ENOUGH_RESOURCES.getMessage(
        "Alluxio Master", "memory", masterMemInMB, resource.getMemory()));
    Client client = new Client(mConf);
    client.run();
  }

}