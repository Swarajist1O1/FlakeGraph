class DummyClass_57204 {
  @Test
  public void testReprocess() throws Exception {
    NSSummary nonExistentSummary =
            reconNamespaceSummaryManager.getNSSummary(BUCKET_ONE_OBJECT_ID);
    Assert.assertNull(nonExistentSummary);

    populateOMDB();

    // write a NSSummary prior to reprocess and verify it got cleaned up after.
    NSSummary staleNSSummary = new NSSummary();
    reconNamespaceSummaryManager.storeNSSummary(-1L, staleNSSummary);
    NSSummaryTask nsSummaryTask = new NSSummaryTask(
            reconNamespaceSummaryManager);
    nsSummaryTask.reprocess(reconOMMetadataManager);

    Assert.assertNull(reconNamespaceSummaryManager.getNSSummary(-1L));
    NSSummary nsSummaryForBucket1 =
            reconNamespaceSummaryManager.getNSSummary(BUCKET_ONE_OBJECT_ID);
    NSSummary nsSummaryForBucket2 =
            reconNamespaceSummaryManager.getNSSummary(BUCKET_TWO_OBJECT_ID);
    Assert.assertNotNull(nsSummaryForBucket1);
    Assert.assertNotNull(nsSummaryForBucket2);

    Assert.assertEquals(1, nsSummaryForBucket1.getNumOfFiles());
    Assert.assertEquals(2, nsSummaryForBucket2.getNumOfFiles());

    Assert.assertEquals(KEY_ONE_SIZE, nsSummaryForBucket1.getSizeOfFiles());
    Assert.assertEquals(KEY_TWO_OLD_SIZE + KEY_FOUR_SIZE,
            nsSummaryForBucket2.getSizeOfFiles());

    int[] fileDistBucket1 = nsSummaryForBucket1.getFileSizeBucket();
    int[] fileDistBucket2 = nsSummaryForBucket2.getFileSizeBucket();
    Assert.assertEquals(ReconConstants.NUM_OF_BINS, fileDistBucket1.length);
    Assert.assertEquals(ReconConstants.NUM_OF_BINS, fileDistBucket2.length);

    Assert.assertEquals(1, fileDistBucket1[0]);
    for (int i = 1; i < ReconConstants.NUM_OF_BINS; ++i) {
      Assert.assertEquals(0, fileDistBucket1[i]);
    }
    Assert.assertEquals(1, fileDistBucket2[1]);
    Assert.assertEquals(1, fileDistBucket2[2]);
    for (int i = 0; i < ReconConstants.NUM_OF_BINS; ++i) {
      if (i == 1 || i == 2) {
        continue;
      }
      Assert.assertEquals(0, fileDistBucket2[i]);
    }

    // Bucket one has one dir, bucket two has none.
    Set<Long> childDirBucketOne = nsSummaryForBucket1.getChildDir();
    Set<Long> childDirBucketTwo = nsSummaryForBucket2.getChildDir();
    Assert.assertEquals(1, childDirBucketOne.size());
    bucketOneAns.clear();
    bucketOneAns.add(DIR_ONE_OBJECT_ID);
    Assert.assertEquals(bucketOneAns, childDirBucketOne);
    Assert.assertEquals(0, childDirBucketTwo.size());

    // Dir 1 has two dir: dir2 and dir3.
    NSSummary nsSummaryInDir1 = reconNamespaceSummaryManager
            .getNSSummary(DIR_ONE_OBJECT_ID);
    Assert.assertNotNull(nsSummaryInDir1);
    Set<Long> childDirForDirOne = nsSummaryInDir1.getChildDir();
    Assert.assertEquals(2, childDirForDirOne.size());
    dirOneAns.clear();
    dirOneAns.add(DIR_TWO_OBJECT_ID);
    dirOneAns.add(DIR_THREE_OBJECT_ID);
    Assert.assertEquals(dirOneAns, childDirForDirOne);

    NSSummary nsSummaryInDir2 = reconNamespaceSummaryManager
            .getNSSummary(DIR_TWO_OBJECT_ID);
    Assert.assertEquals(1, nsSummaryInDir2.getNumOfFiles());
    Assert.assertEquals(KEY_THREE_SIZE, nsSummaryInDir2.getSizeOfFiles());

    int[] fileDistForDir2 = nsSummaryInDir2.getFileSizeBucket();
    Assert.assertEquals(ReconConstants.NUM_OF_BINS, fileDistForDir2.length);
    Assert.assertEquals(1, fileDistForDir2[fileDistForDir2.length - 1]);
    for (int i = 0; i < ReconConstants.NUM_OF_BINS - 1; ++i) {
      Assert.assertEquals(0, fileDistForDir2[i]);
    }
    Assert.assertEquals(0, nsSummaryInDir2.getChildDir().size());

    // bucket should have empty dirName
    Assert.assertEquals(0, nsSummaryForBucket1.getDirName().length());
    Assert.assertEquals(0, nsSummaryForBucket2.getDirName().length());
    // check dirName is correctly written
    Assert.assertEquals(DIR_ONE, nsSummaryInDir1.getDirName());
    Assert.assertEquals(DIR_TWO, nsSummaryInDir2.getDirName());
  }

}