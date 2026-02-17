class DummyClass_57293 {
  @Test
  public void testStoreContainerKeyMapping() throws Exception {

    long containerId = System.currentTimeMillis();
    Map<String, Integer> prefixCounts = new HashMap<>();
    prefixCounts.put(keyPrefix1, 1);
    prefixCounts.put(keyPrefix2, 2);
    prefixCounts.put(keyPrefix3, 3);

    for (Map.Entry<String, Integer> entry : prefixCounts.entrySet()) {
      ContainerKeyPrefix containerKeyPrefix = new ContainerKeyPrefix(
          containerId, entry.getKey(), 0);
      reconContainerMetadataManager.storeContainerKeyMapping(
          containerKeyPrefix, prefixCounts.get(entry.getKey()));
    }

    Assert.assertEquals(1,
        reconContainerMetadataManager.getCountForContainerKeyPrefix(
            new ContainerKeyPrefix(containerId, keyPrefix1,
                0)).longValue());
    Assert.assertEquals(2,
        reconContainerMetadataManager.getCountForContainerKeyPrefix(
            new ContainerKeyPrefix(containerId, keyPrefix2,
                0)).longValue());
    Assert.assertEquals(3,
        reconContainerMetadataManager.getCountForContainerKeyPrefix(
            new ContainerKeyPrefix(containerId, keyPrefix3,
                0)).longValue());
  }

}