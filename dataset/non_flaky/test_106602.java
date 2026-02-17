class DummyClass_106602 {
  @Test
  public void notEnoughVCoreForApplicationMaster() throws Exception {
    int appMasterMem = 1024;
    int appMasterCore = 2;
    Resource resource = Resource.newInstance(appMasterMem, appMasterCore / 2);
    generateMaxAllocation(resource);
    mThrown.expect(RuntimeException.class);
    mThrown.expectMessage(ExceptionMessage.YARN_NOT_ENOUGH_RESOURCES.getMessage(
        "ApplicationMaster", "virtual cores", appMasterCore, resource.getVirtualCores()));
    String[] args = new String[] {
        "-resource_path", "test",
        "-am_memory", Integer.toString(appMasterMem),
        "-am_vcores", Integer.toString(appMasterCore)
    };
    Client client = new Client(args, mConf);
    client.run();
  }

}