class DummyClass_106596 {
  @Test(timeout = 10000)
  public void oneContainerPerHostFullAllocation() throws Exception {
    int numHosts = 10;
    int maxContainersPerHost = 1;
    testFullAllocation(numHosts, maxContainersPerHost);
  }

}