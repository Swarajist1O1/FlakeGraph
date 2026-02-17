class DummyClass_106600 {
  @Test(timeout = 1000)
  public void allocateMasterInAnyHost() throws Exception {
    ContainerAllocator containerAllocator = new ContainerAllocator(CONTAINER_NAME, 1,
        1, mResource, mYarnClient, mRMClient, "any");
    doAnswer(allocateFirstHostAnswer(containerAllocator))
        .when(mRMClient).addContainerRequest(Matchers.argThat(request -> {
          if (request.getRelaxLocality() == true
              && request.getNodes().size() == 1
              && request.getNodes().get(0).equals("any")) {
            return true;
          }
          return false;
        }));
    containerAllocator.allocateContainers();
  }

}