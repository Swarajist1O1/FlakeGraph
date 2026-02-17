class DummyClass_57292 {
  @Test
  public void testInitNewContainerDB() throws Exception {
    long containerId = System.currentTimeMillis();
    Map<ContainerKeyPrefix, Integer> prefixCounts = new HashMap<>();

    ContainerKeyPrefix ckp1 = new ContainerKeyPrefix(containerId,
        "V1/B1/K1", 0);
    prefixCounts.put(ckp1, 1);

    ContainerKeyPrefix ckp2 = new ContainerKeyPrefix(containerId,
        "V1/B1/K2", 0);
    prefixCounts.put(ckp2, 2);

    ContainerKeyPrefix ckp3 = new ContainerKeyPrefix(containerId,
        "V1/B2/K3", 0);
    prefixCounts.put(ckp3, 3);

    for (Map.Entry<ContainerKeyPrefix, Integer> entry :
        prefixCounts.entrySet()) {
      reconContainerMetadataManager.storeContainerKeyMapping(
          entry.getKey(), prefixCounts.get(entry.getKey()));
    }

    assertEquals(1, reconContainerMetadataManager
        .getCountForContainerKeyPrefix(ckp1).intValue());

    prefixCounts.clear();
    prefixCounts.put(ckp2, 12);
    prefixCounts.put(ckp3, 13);
    ContainerKeyPrefix ckp4 = new ContainerKeyPrefix(containerId,
        "V1/B3/K1", 0);
    prefixCounts.put(ckp4, 14);
    ContainerKeyPrefix ckp5 = new ContainerKeyPrefix(containerId,
        "V1/B3/K2", 0);
    prefixCounts.put(ckp5, 15);

    reconContainerMetadataManager
            .reinitWithNewContainerDataFromOm(prefixCounts);
    Map<ContainerKeyPrefix, Integer> keyPrefixesForContainer =
        reconContainerMetadataManager.getKeyPrefixesForContainer(containerId);

    assertEquals(4, keyPrefixesForContainer.size());
    assertEquals(12, keyPrefixesForContainer.get(ckp2).intValue());
    assertEquals(13, keyPrefixesForContainer.get(ckp3).intValue());
    assertEquals(14, keyPrefixesForContainer.get(ckp4).intValue());
    assertEquals(15, keyPrefixesForContainer.get(ckp5).intValue());

    assertEquals(0, reconContainerMetadataManager
        .getCountForContainerKeyPrefix(ckp1).intValue());
  }

}