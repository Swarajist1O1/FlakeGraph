class DummyClass_106601 {
  @Test
  public void notEnoughMemoryForApplicationMaster() throws Exception {
    int appMasterMem = 1024;
    Resource resource = Resource.newInstance(appMasterMem / 2, 4);
    generateMaxAllocation(resource);
    mThrown.expect(RuntimeException.class);
    mThrown.expectMessage(ExceptionMessage.YARN_NOT_ENOUGH_RESOURCES.getMessage(
        "ApplicationMaster", "memory", appMasterMem, resource.getMemory()));
    String[] args = new String[] {
        "-resource_path", "test",
        "-am_memory", Integer.toString(appMasterMem),
        "-am_vcores", "2"
    };
    Client client = new Client(args, mConf);
    client.run();
  }

}