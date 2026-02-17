class DummyClass_57266 {
  @Test
  public void testDiskUsage() throws Exception {
    // volume level DU
    Response volResponse = nsSummaryEndpoint.getDiskUsage(VOL_PATH,
            false, false);
    DUResponse duVolRes = (DUResponse) volResponse.getEntity();
    Assert.assertEquals(2, duVolRes.getCount());
    List<DUResponse.DiskUsage> duData = duVolRes.getDuData();
    // sort based on subpath
    Collections.sort(duData,
            Comparator.comparing(DUResponse.DiskUsage::getSubpath));
    DUResponse.DiskUsage duBucket1 = duData.get(0);
    DUResponse.DiskUsage duBucket2 = duData.get(1);
    Assert.assertEquals(BUCKET_ONE_PATH, duBucket1.getSubpath());
    Assert.assertEquals(BUCKET_TWO_PATH, duBucket2.getSubpath());
    Assert.assertEquals(BUCKET_ONE_DATA_SIZE, duBucket1.getSize());
    Assert.assertEquals(BUCKET_TWO_DATA_SIZE, duBucket2.getSize());

    // bucket level DU
    Response bucketResponse = nsSummaryEndpoint.getDiskUsage(BUCKET_ONE_PATH,
            false, false);
    DUResponse duBucketResponse = (DUResponse) bucketResponse.getEntity();
    Assert.assertEquals(1, duBucketResponse.getCount());
    DUResponse.DiskUsage duDir1 = duBucketResponse.getDuData().get(0);
    Assert.assertEquals(DIR_ONE_PATH, duDir1.getSubpath());
    Assert.assertEquals(DIR_ONE_DATA_SIZE, duDir1.getSize());

    // dir level DU
    Response dirResponse = nsSummaryEndpoint.getDiskUsage(DIR_ONE_PATH,
            false, false);
    DUResponse duDirReponse = (DUResponse) dirResponse.getEntity();
    Assert.assertEquals(3, duDirReponse.getCount());
    List<DUResponse.DiskUsage> duSubDir = duDirReponse.getDuData();
    Collections.sort(duSubDir,
            Comparator.comparing(DUResponse.DiskUsage::getSubpath));
    DUResponse.DiskUsage duDir2 = duSubDir.get(0);
    DUResponse.DiskUsage duDir3 = duSubDir.get(1);
    DUResponse.DiskUsage duDir4 = duSubDir.get(2);
    Assert.assertEquals(DIR_TWO_PATH, duDir2.getSubpath());
    Assert.assertEquals(KEY_TWO_SIZE, duDir2.getSize());

    Assert.assertEquals(DIR_THREE_PATH, duDir3.getSubpath());
    Assert.assertEquals(KEY_THREE_SIZE, duDir3.getSize());

    Assert.assertEquals(DIR_FOUR_PATH, duDir4.getSubpath());
    Assert.assertEquals(KEY_SIX_SIZE, duDir4.getSize());

    // key level DU
    Response keyResponse = nsSummaryEndpoint.getDiskUsage(KEY_PATH,
            false, false);
    DUResponse keyObj = (DUResponse) keyResponse.getEntity();
    Assert.assertEquals(0, keyObj.getCount());
    Assert.assertEquals(KEY_FOUR_SIZE, keyObj.getSize());

    // invalid path check
    Response invalidResponse = nsSummaryEndpoint.getDiskUsage(INVALID_PATH,
            false, false);
    DUResponse invalidObj = (DUResponse) invalidResponse.getEntity();
    Assert.assertEquals(ResponseStatus.PATH_NOT_FOUND,
            invalidObj.getStatus());
  }

}