class DummyClass_57283 {
  @Test
  public void testUnhealthyContainersFilteredResponse() throws IOException {
    String missing =  UnHealthyContainerStates.MISSING.toString();

    Response response = containerEndpoint
        .getUnhealthyContainers(missing, 1000, 1);

    UnhealthyContainersResponse responseObject =
        (UnhealthyContainersResponse) response.getEntity();

    assertEquals(0, responseObject.getMissingCount());
    assertEquals(0, responseObject.getOverReplicatedCount());
    assertEquals(0, responseObject.getUnderReplicatedCount());
    assertEquals(0, responseObject.getMisReplicatedCount());
    assertEquals(Collections.EMPTY_LIST, responseObject.getContainers());

    putContainerInfos(5);
    uuid1 = newDatanode("host1", "127.0.0.1");
    uuid2 = newDatanode("host2", "127.0.0.2");
    uuid3 = newDatanode("host3", "127.0.0.3");
    uuid4 = newDatanode("host4", "127.0.0.4");
    createUnhealthyRecords(5, 4, 3, 2);

    response = containerEndpoint.getUnhealthyContainers(missing, 1000, 1);

    responseObject = (UnhealthyContainersResponse) response.getEntity();
    // Summary should have the count for all unhealthy:
    assertEquals(5, responseObject.getMissingCount());
    assertEquals(4, responseObject.getOverReplicatedCount());
    assertEquals(3, responseObject.getUnderReplicatedCount());
    assertEquals(2, responseObject.getMisReplicatedCount());

    Collection<UnhealthyContainerMetadata> records
        = responseObject.getContainers();

    // There should only be 5 missing containers and no others as we asked for
    // only missing.
    assertEquals(5, records.size());
    for (UnhealthyContainerMetadata r : records) {
      assertEquals(missing, r.getContainerState());
    }
  }

}