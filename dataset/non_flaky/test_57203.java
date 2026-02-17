class DummyClass_57203 {
  @Test
  public void testProcessOMEvents() throws IOException {
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

    String bucket = "bucketOne";
    String volume = "sampleVol";
    String key = "key_one";
    String omKey = omMetadataManager.getOzoneKey(volume, bucket, key);
    OmKeyInfo omKeyInfo = buildOmKeyInfo(volume, bucket, key,
        omKeyLocationInfoGroup);

    OMDBUpdateEvent keyEvent1 = new OMDBUpdateEvent.
        OMUpdateEventBuilder<String, OmKeyInfo>()
        .setKey(omKey)
        .setValue(omKeyInfo)
        .setTable(omMetadataManager.getKeyTable(getBucketLayout()).getName())
        .setAction(OMDBUpdateEvent.OMDBUpdateAction.PUT)
        .build();

    BlockID blockID3 = new BlockID(1, 2);
    OmKeyLocationInfo omKeyLocationInfo3 =
        getOmKeyLocationInfo(blockID3, pipeline);

    BlockID blockID4 = new BlockID(3, 1);
    OmKeyLocationInfo omKeyLocationInfo4
        = getOmKeyLocationInfo(blockID4, pipeline);

    omKeyLocationInfoList = new ArrayList<>();
    omKeyLocationInfoList.add(omKeyLocationInfo3);
    omKeyLocationInfoList.add(omKeyLocationInfo4);
    omKeyLocationInfoGroup = new OmKeyLocationInfoGroup(0,
        omKeyLocationInfoList);

    String key2 = "key_two";
    writeDataToOm(reconOMMetadataManager, key2, bucket, volume, Collections
        .singletonList(omKeyLocationInfoGroup));

    omKey = omMetadataManager.getOzoneKey(volume, bucket, key2);
    OMDBUpdateEvent keyEvent2 = new OMDBUpdateEvent.
        OMUpdateEventBuilder<String, OmKeyInfo>()
        .setKey(omKey)
        .setAction(OMDBUpdateEvent.OMDBUpdateAction.DELETE)
        .setTable(omMetadataManager.getKeyTable(getBucketLayout()).getName())
        .build();

    OMUpdateEventBatch omUpdateEventBatch = new OMUpdateEventBatch(new
        ArrayList<OMDBUpdateEvent>() {{
          add(keyEvent1);
          add(keyEvent2);
        }});

    ContainerKeyMapperTask containerKeyMapperTask =
        new ContainerKeyMapperTask(reconContainerMetadataManager);
    containerKeyMapperTask.reprocess(reconOMMetadataManager);

    keyPrefixesForContainer = reconContainerMetadataManager
        .getKeyPrefixesForContainer(1);
    assertEquals(1, keyPrefixesForContainer.size());

    keyPrefixesForContainer = reconContainerMetadataManager
        .getKeyPrefixesForContainer(2);
    assertTrue(keyPrefixesForContainer.isEmpty());

    keyPrefixesForContainer = reconContainerMetadataManager
        .getKeyPrefixesForContainer(3);
    assertEquals(1, keyPrefixesForContainer.size());

    assertEquals(1, reconContainerMetadataManager.getKeyCountForContainer(1L));
    assertEquals(0, reconContainerMetadataManager.getKeyCountForContainer(2L));
    assertEquals(1, reconContainerMetadataManager.getKeyCountForContainer(3L));

    // Process PUT & DELETE event.
    containerKeyMapperTask.process(omUpdateEventBatch);

    keyPrefixesForContainer = reconContainerMetadataManager
        .getKeyPrefixesForContainer(1);
    assertEquals(1, keyPrefixesForContainer.size());

    keyPrefixesForContainer = reconContainerMetadataManager
        .getKeyPrefixesForContainer(2);
    assertEquals(1, keyPrefixesForContainer.size());

    keyPrefixesForContainer = reconContainerMetadataManager
        .getKeyPrefixesForContainer(3);
    assertTrue(keyPrefixesForContainer.isEmpty());

    assertEquals(1, reconContainerMetadataManager.getKeyCountForContainer(1L));
    assertEquals(1, reconContainerMetadataManager.getKeyCountForContainer(2L));
    assertEquals(0, reconContainerMetadataManager.getKeyCountForContainer(3L));

    // Test if container count is updated
    assertEquals(3, reconContainerMetadataManager.getCountForContainers());
  }

}