class DummyClass_57202 {
  @Test
  public void testReprocessOMDB() throws Exception{

    Map<ContainerKeyPrefix, Integer> keyPrefixesForContainer =
        reconContainerMetadataManager.getKeyPrefixesForContainer(1);
    assertTrue(keyPrefixesForContainer.isEmpty());

    keyPrefixesForContainer = reconContainerMetadataManager
        .getKeyPrefixesForContainer(2);
    assertTrue(keyPrefixesForContainer.isEmpty());

    Pipeline pipeline = getRandomPipeline();

    List<OmKeyLocationInfo> omKeyLocationInfoList = new ArrayList<>();
    BlockID blockID1 = new BlockID(1, 1);
    OmKeyLocationInfo omKeyLocationInfo1 = getOmKeyLocationInfo(blockID1,
        pipeline);

    BlockID blockID2 = new BlockID(2, 1);
    OmKeyLocationInfo omKeyLocationInfo2
        = getOmKeyLocationInfo(blockID2, pipeline);

    omKeyLocationInfoList.add(omKeyLocationInfo1);
    omKeyLocationInfoList.add(omKeyLocationInfo2);

    OmKeyLocationInfoGroup omKeyLocationInfoGroup = new
        OmKeyLocationInfoGroup(0, omKeyLocationInfoList);

    writeDataToOm(reconOMMetadataManager,
        "key_one",
        "bucketOne",
        "sampleVol",
        Collections.singletonList(omKeyLocationInfoGroup));

    ContainerKeyMapperTask containerKeyMapperTask =
        new ContainerKeyMapperTask(reconContainerMetadataManager);
    containerKeyMapperTask.reprocess(reconOMMetadataManager);

    keyPrefixesForContainer =
        reconContainerMetadataManager.getKeyPrefixesForContainer(1);
    assertEquals(1, keyPrefixesForContainer.size());
    String omKey = omMetadataManager.getOzoneKey("sampleVol",
        "bucketOne", "key_one");
    ContainerKeyPrefix containerKeyPrefix = new ContainerKeyPrefix(1,
        omKey, 0);
    assertEquals(1,
        keyPrefixesForContainer.get(containerKeyPrefix).intValue());

    keyPrefixesForContainer =
        reconContainerMetadataManager.getKeyPrefixesForContainer(2);
    assertEquals(1, keyPrefixesForContainer.size());
    containerKeyPrefix = new ContainerKeyPrefix(2, omKey,
        0);
    assertEquals(1,
        keyPrefixesForContainer.get(containerKeyPrefix).intValue());

    // Test if container key counts are updated
    assertEquals(1, reconContainerMetadataManager.getKeyCountForContainer(1L));
    assertEquals(1, reconContainerMetadataManager.getKeyCountForContainer(2L));
    assertEquals(0, reconContainerMetadataManager.getKeyCountForContainer(3L));

    // Test if container count is updated
    assertEquals(2, reconContainerMetadataManager.getCountForContainers());
  }

}