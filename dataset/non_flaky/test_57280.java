class DummyClass_57280 {
  @Test
  public void testGetContainersWithPrevKey() {

    Response response = containerEndpoint.getContainers(1, 1L);

    ContainersResponse responseObject =
        (ContainersResponse) response.getEntity();

    ContainersResponse.ContainersResponseData data =
        responseObject.getContainersResponseData();
    assertEquals(2, data.getTotalCount());

    List<ContainerMetadata> containers = new ArrayList<>(data.getContainers());

    Iterator<ContainerMetadata> iterator = containers.iterator();

    ContainerMetadata containerMetadata = iterator.next();

    assertEquals(1, containers.size());
    assertEquals(2L, containerMetadata.getContainerID());

    response = containerEndpoint.getContainers(-1, 0L);
    responseObject = (ContainersResponse) response.getEntity();
    data = responseObject.getContainersResponseData();
    containers = new ArrayList<>(data.getContainers());
    assertEquals(2, containers.size());
    assertEquals(2, data.getTotalCount());
    iterator = containers.iterator();
    containerMetadata = iterator.next();
    assertEquals(1L, containerMetadata.getContainerID());

    // test for negative cases
    response = containerEndpoint.getContainers(-1, 5L);
    responseObject = (ContainersResponse) response.getEntity();
    data = responseObject.getContainersResponseData();
    containers = new ArrayList<>(data.getContainers());
    assertEquals(0, containers.size());
    assertEquals(2, data.getTotalCount());

    response = containerEndpoint.getContainers(-1, -1L);
    responseObject = (ContainersResponse) response.getEntity();
    data = responseObject.getContainersResponseData();
    containers = new ArrayList<>(data.getContainers());
    assertEquals(2, containers.size());
    assertEquals(2, data.getTotalCount());
  }

}