class DummyClass_57285 {
  @Test
  public void testUnhealthyContainersPaging() throws IOException {
    putContainerInfos(6);
    uuid1 = newDatanode("host1", "127.0.0.1");
    uuid2 = newDatanode("host2", "127.0.0.2");
    uuid3 = newDatanode("host3", "127.0.0.3");
    uuid4 = newDatanode("host4", "127.0.0.4");
    createUnhealthyRecords(5, 4, 3, 2);
    UnhealthyContainersResponse firstBatch =
        (UnhealthyContainersResponse) containerEndpoint.getUnhealthyContainers(
            3, 1).getEntity();

    UnhealthyContainersResponse secondBatch =
        (UnhealthyContainersResponse) containerEndpoint.getUnhealthyContainers(
            3, 2).getEntity();

    ArrayList<UnhealthyContainerMetadata> records
        = new ArrayList<>(firstBatch.getContainers());
    assertEquals(3, records.size());
    assertEquals(1L, records.get(0).getContainerID());
    assertEquals(2L, records.get(1).getContainerID());
    assertEquals(3L, records.get(2).getContainerID());

    records
        = new ArrayList<>(secondBatch.getContainers());
    assertEquals(3, records.size());
    assertEquals(4L, records.get(0).getContainerID());
    assertEquals(5L, records.get(1).getContainerID());
    assertEquals(6L, records.get(2).getContainerID());
  }

}