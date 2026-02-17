class DummyClass_106599 {
  @Test(timeout = 10000)
  public void notEnoughHosts() throws Exception {
    int numHosts = 10;
    int maxContainersPerHost = 5;
    int numContainers = numHosts * maxContainersPerHost + 1; // one container too many
    ContainerAllocator containerAllocator =
        setup(numHosts, maxContainersPerHost, numContainers);
    mThrown.expect(RuntimeException.class);
    mThrown.expectMessage(
        ExceptionMessage.YARN_NOT_ENOUGH_HOSTS.getMessage(numContainers, CONTAINER_NAME, numHosts));
    containerAllocator.allocateContainers();
  }

}