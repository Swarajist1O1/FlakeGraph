class DummyClass_106598 {
  @Test(timeout = 10000)
  public void fiveContainersPerHostHalfAllocation() throws Exception {
    int numHosts = 10;
    int maxContainersPerHost = 5;
    int numContainers = numHosts * maxContainersPerHost / 2;
    ContainerAllocator containerAllocator =
        setup(numHosts, maxContainersPerHost, numContainers);
    List<Container> containers = containerAllocator.allocateContainers();

    assertEquals(numContainers, containers.size());
    checkMaxHostsLimitNotExceeded(containers, maxContainersPerHost);
  }

}