class DummyClass_57205 {
  @Test
  public void testProcess() throws Exception {
    NSSummary nonExistentSummary =
            reconNamespaceSummaryManager.getNSSummary(BUCKET_ONE_OBJECT_ID);
    Assert.assertNull(nonExistentSummary);

    populateOMDB();

    // Events for keyTable change:
    // put file5 under bucket 2
    String omPutKey = BUCKET_TWO_OBJECT_ID + OM_KEY_PREFIX + FILE_FIVE;
    OmKeyInfo omPutKeyInfo = buildOmKeyInfo(VOL, BUCKET_TWO, KEY_FIVE,
            FILE_FIVE, KEY_FIVE_OBJECT_ID, BUCKET_TWO_OBJECT_ID, KEY_FIVE_SIZE);
    OMDBUpdateEvent keyEvent1 = new OMDBUpdateEvent.
            OMUpdateEventBuilder<String, OmKeyInfo>()
            .setKey(omPutKey)
            .setValue(omPutKeyInfo)
            .setTable(omMetadataManager.getKeyTable(getBucketLayout())
            .getName())
            .setAction(OMDBUpdateEvent.OMDBUpdateAction.PUT)
            .build();

    // delete file 1 under bucket 1
    String omDeleteKey = BUCKET_ONE_OBJECT_ID + OM_KEY_PREFIX + FILE_ONE;
    OmKeyInfo omDeleteInfo = buildOmKeyInfo(VOL, BUCKET_ONE, KEY_ONE, FILE_ONE,
            KEY_ONE_OBJECT_ID, BUCKET_ONE_OBJECT_ID);
    OMDBUpdateEvent keyEvent2 = new OMDBUpdateEvent.
            OMUpdateEventBuilder<String, OmKeyInfo>()
            .setKey(omDeleteKey)
            .setValue(omDeleteInfo)
            .setTable(omMetadataManager.getKeyTable(getBucketLayout())
            .getName())
            .setAction(OMDBUpdateEvent.OMDBUpdateAction.DELETE)
            .build();

    // update file 2's size under bucket 2
    String omUpdateKey = BUCKET_TWO_OBJECT_ID + OM_KEY_PREFIX + FILE_TWO;
    OmKeyInfo omOldInfo = buildOmKeyInfo(VOL, BUCKET_TWO, KEY_TWO, FILE_TWO,
            KEY_TWO_OBJECT_ID, BUCKET_TWO_OBJECT_ID, KEY_TWO_OLD_SIZE);
    OmKeyInfo omUpdateInfo = buildOmKeyInfo(VOL, BUCKET_TWO, KEY_TWO, FILE_TWO,
            KEY_TWO_OBJECT_ID, BUCKET_TWO_OBJECT_ID, KEY_TWO_UPDATE_SIZE);
    OMDBUpdateEvent keyEvent3 = new OMDBUpdateEvent.
            OMUpdateEventBuilder<String, OmKeyInfo>()
            .setKey(omUpdateKey)
            .setValue(omUpdateInfo)
            .setOldValue(omOldInfo)
            .setTable(omMetadataManager.getKeyTable(getBucketLayout())
            .getName())
            .setAction(OMDBUpdateEvent.OMDBUpdateAction.UPDATE)
            .build();

    // Events for DirectoryTable change:
    // add dir 4 under bucket 1
    String omDirPutKey1 = BUCKET_ONE_OBJECT_ID + OM_KEY_PREFIX + DIR_FOUR;
    OmDirectoryInfo omDirPutValue1 = buildOmDirInfo(DIR_FOUR,
            DIR_FOUR_OBJECT_ID, BUCKET_ONE_OBJECT_ID);
    OMDBUpdateEvent keyEvent4 = new OMDBUpdateEvent.
            OMUpdateEventBuilder<String, OmDirectoryInfo>()
            .setKey(omDirPutKey1)
            .setValue(omDirPutValue1)
            .setAction(OMDBUpdateEvent.OMDBUpdateAction.PUT)
            .setTable(omMetadataManager.getDirectoryTable().getName())
            .build();

    // add dir 5 under bucket 2
    String omDirPutKey2 = BUCKET_TWO_OBJECT_ID + OM_KEY_PREFIX + DIR_FIVE;
    OmDirectoryInfo omDirPutValue2 = buildOmDirInfo(DIR_FIVE,
            DIR_FIVE_OBJECT_ID, BUCKET_TWO_OBJECT_ID);
    OMDBUpdateEvent keyEvent5 = new OMDBUpdateEvent.
            OMUpdateEventBuilder<String, OmDirectoryInfo>()
            .setKey(omDirPutKey2)
            .setValue(omDirPutValue2)
            .setAction(OMDBUpdateEvent.OMDBUpdateAction.PUT)
            .setTable(omMetadataManager.getDirectoryTable().getName())
            .build();

    // delete dir 3 under dir 1
    String omDirDeleteKey = DIR_ONE_OBJECT_ID + OM_KEY_PREFIX + DIR_THREE;
    OmDirectoryInfo omDirDeleteValue = buildOmDirInfo(DIR_FIVE,
            DIR_THREE_OBJECT_ID, DIR_ONE_OBJECT_ID);
    OMDBUpdateEvent keyEvent6 = new OMDBUpdateEvent.
            OMUpdateEventBuilder<String, OmDirectoryInfo>()
            .setKey(omDirDeleteKey)
            .setValue(omDirDeleteValue)
            .setAction(OMDBUpdateEvent.OMDBUpdateAction.DELETE)
            .setTable(omMetadataManager.getDirectoryTable().getName())
            .build();

    // rename dir1
    String omDirUpdateKey = BUCKET_ONE_OBJECT_ID + OM_KEY_PREFIX + DIR_ONE;
    OmDirectoryInfo omDirOldValue = buildOmDirInfo(DIR_ONE,
            DIR_ONE_OBJECT_ID, BUCKET_ONE_OBJECT_ID);
    OmDirectoryInfo omDirUpdateValue = buildOmDirInfo(DIR_ONE_RENAME,
            DIR_ONE_OBJECT_ID, BUCKET_ONE_OBJECT_ID);
    OMDBUpdateEvent keyEvent7 = new OMDBUpdateEvent.
            OMUpdateEventBuilder<String, OmDirectoryInfo>()
            .setKey(omDirUpdateKey)
            .setValue(omDirUpdateValue)
            .setOldValue(omDirOldValue)
            .setAction(OMDBUpdateEvent.OMDBUpdateAction.UPDATE)
            .setTable(omMetadataManager.getDirectoryTable().getName())
            .build();

    OMUpdateEventBatch omUpdateEventBatch = new OMUpdateEventBatch(
            new ArrayList<OMDBUpdateEvent>() {{
              add(keyEvent1);
              add(keyEvent2);
              add(keyEvent3);
              add(keyEvent4);
              add(keyEvent5);
              add(keyEvent6);
              add(keyEvent7);
          }});

    NSSummaryTask nsSummaryTask = new NSSummaryTask(
            reconNamespaceSummaryManager);
    nsSummaryTask.reprocess(reconOMMetadataManager);
    nsSummaryTask.process(omUpdateEventBatch);

    // file 5 is added under bucket 2, so bucket 2 has 3 keys now
    // file 1 is gone, so bucket 1 is empty now
    // file 2 is updated with new datasize,
    // so file size dist for bucket 2 should be updated
    NSSummary nsSummaryForBucket1 =
            reconNamespaceSummaryManager.getNSSummary(BUCKET_ONE_OBJECT_ID);
    Assert.assertNotNull(nsSummaryForBucket1);
    Assert.assertEquals(0, nsSummaryForBucket1.getNumOfFiles());

    Set<Long> childDirBucket1 = nsSummaryForBucket1.getChildDir();
    // after put dir4, bucket1 now has two child dirs: dir1 and dir4
    Assert.assertEquals(2, childDirBucket1.size());
    bucketOneAns.clear();
    bucketOneAns.add(DIR_ONE_OBJECT_ID);
    bucketOneAns.add(DIR_FOUR_OBJECT_ID);
    Assert.assertEquals(bucketOneAns, childDirBucket1);

    NSSummary nsSummaryForBucket2 =
            reconNamespaceSummaryManager.getNSSummary(BUCKET_TWO_OBJECT_ID);
    Assert.assertNotNull(nsSummaryForBucket2);
    Assert.assertEquals(3, nsSummaryForBucket2.getNumOfFiles());
    // key 4 + key 5 + updated key 2
    Assert.assertEquals(KEY_FOUR_SIZE + KEY_FIVE_SIZE + KEY_TWO_UPDATE_SIZE,
            nsSummaryForBucket2.getSizeOfFiles());

    int[] fileSizeDist = nsSummaryForBucket2.getFileSizeBucket();
    Assert.assertEquals(ReconConstants.NUM_OF_BINS, fileSizeDist.length);
    // 1023L and 100L
    Assert.assertEquals(2, fileSizeDist[0]);
    // 2050L
    Assert.assertEquals(1, fileSizeDist[2]);
    for (int i = 0; i < ReconConstants.NUM_OF_BINS; ++i) {
      if (i == 0 || i == 2) {
        continue;
      }
      Assert.assertEquals(0, fileSizeDist[i]);
    }

    // after put dir5, bucket 2 now has one dir
    Set<Long> childDirBucket2 = nsSummaryForBucket2.getChildDir();
    Assert.assertEquals(1, childDirBucket2.size());
    bucketTwoAns.add(DIR_FIVE_OBJECT_ID);
    Assert.assertEquals(bucketTwoAns, childDirBucket2);

    // after delete dir 3, dir 1 now has only one dir: dir2
    NSSummary nsSummaryForDir1 = reconNamespaceSummaryManager
            .getNSSummary(DIR_ONE_OBJECT_ID);
    Assert.assertNotNull(nsSummaryForDir1);
    Set<Long> childDirForDir1 = nsSummaryForDir1.getChildDir();
    Assert.assertEquals(1, childDirForDir1.size());
    dirOneAns.clear();
    dirOneAns.add(DIR_TWO_OBJECT_ID);
    Assert.assertEquals(dirOneAns, childDirForDir1);

    // after renaming dir1, check its new name
    Assert.assertEquals(DIR_ONE_RENAME, nsSummaryForDir1.getDirName());
  }

}