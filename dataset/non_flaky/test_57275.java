class DummyClass_57275 {
  @Test
  public void testOpenContainerCount() throws Exception {
    // In case of pipeline doesn't exist
    waitAndCheckConditionAfterHeartbeat(() -> {

      DatanodeMetadata datanodeMetadata1 = getDatanodeMetadata();
      return datanodeMetadata1.getContainers() == 10
              && datanodeMetadata1.getPipelines().size() == 2;
    });

    DatanodeMetadata datanodeMetadata = getDatanodeMetadata();

    int expectedCnt = datanodeMetadata.getOpenContainers();

    // check if open container's count decrement according
    for (long id = 1L; id <= 10L; ++id) {
      --expectedCnt;
      closeContainer(id);
      DatanodeMetadata metadata = getDatanodeMetadata();
      Assert.assertEquals(expectedCnt, metadata.getOpenContainers());
    }
  }

}