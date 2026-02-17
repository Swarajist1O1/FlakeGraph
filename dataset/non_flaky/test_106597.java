class DummyClass_106597 {
  @Test(timeout = 10000)
  public void fiveContainersPerHostFullAllocation() throws Exception {
    int numHosts = 10;
    int maxContainersPerHost = 5;
    testFullAllocation(numHosts, maxContainersPerHost);
  }

}