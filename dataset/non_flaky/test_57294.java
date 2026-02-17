class DummyClass_57294 {
  @Test
  public void testStoreContainerKeyCount() throws Exception {
    long containerId = 1L;
    long nextContainerId = 2L;
    reconContainerMetadataManager.storeContainerKeyCount(containerId, 2L);
    reconContainerMetadataManager.storeContainerKeyCount(nextContainerId, 3L);

    assertEquals(2,
        reconContainerMetadataManager.getKeyCountForContainer(containerId));
    assertEquals(3,
        reconContainerMetadataManager.getKeyCountForContainer(nextContainerId));

    reconContainerMetadataManager.storeContainerKeyCount(containerId, 20L);
    assertEquals(20,
        reconContainerMetadataManager.getKeyCountForContainer(containerId));
  }

}