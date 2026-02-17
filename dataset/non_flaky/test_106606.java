class DummyClass_106606 {
  @Test
  public void notEnoughVCoreForAlluxioWorker() throws Exception {
    mConf.set(PropertyKey.INTEGRATION_WORKER_RESOURCE_MEM, "2048.00MB");
    mConf.set(PropertyKey.WORKER_RAMDISK_SIZE, "4096.00MB");
    mConf.set(PropertyKey.INTEGRATION_WORKER_RESOURCE_CPU, "8");
    int workerMemInMB = (int) (mConf.getBytes(
        PropertyKey.INTEGRATION_WORKER_RESOURCE_MEM) / Constants.MB);
    int ramdiskMemInMB = (int) (mConf.getBytes(
        PropertyKey.WORKER_RAMDISK_SIZE) / Constants.MB);
    int workerVCore = mConf.getInt(PropertyKey.INTEGRATION_WORKER_RESOURCE_CPU);
    Resource resource = Resource.newInstance((workerMemInMB + ramdiskMemInMB), 4);
    generateMaxAllocation(resource);
    mThrown.expect(RuntimeException.class);
    mThrown.expectMessage(ExceptionMessage.YARN_NOT_ENOUGH_RESOURCES.getMessage(
        "Alluxio Worker", "virtual cores", workerVCore, resource.getVirtualCores()));
    Client client = new Client(mConf);
    client.run();
  }

}