class DummyClass_57265 {
  @Test
  public void testBasic() throws Exception {
    // Test volume basics
    Response volResponse = nsSummaryEndpoint.getBasicInfo(VOL_PATH);
    NamespaceSummaryResponse volResponseObj =
            (NamespaceSummaryResponse) volResponse.getEntity();
    Assert.assertEquals(EntityType.VOLUME, volResponseObj.getEntityType());
    Assert.assertEquals(2, volResponseObj.getNumBucket());
    Assert.assertEquals(4, volResponseObj.getNumTotalDir());
    Assert.assertEquals(6, volResponseObj.getNumTotalKey());

    // Test bucket 1's basics
    Response bucketOneResponse =
            nsSummaryEndpoint.getBasicInfo(BUCKET_ONE_PATH);
    NamespaceSummaryResponse bucketOneObj =
            (NamespaceSummaryResponse) bucketOneResponse.getEntity();
    Assert.assertEquals(EntityType.BUCKET, bucketOneObj.getEntityType());
    Assert.assertEquals(4, bucketOneObj.getNumTotalDir());
    Assert.assertEquals(4, bucketOneObj.getNumTotalKey());

    // Test bucket 2's basics
    Response bucketTwoResponse =
            nsSummaryEndpoint.getBasicInfo(BUCKET_TWO_PATH);
    NamespaceSummaryResponse bucketTwoObj =
            (NamespaceSummaryResponse) bucketTwoResponse.getEntity();
    Assert.assertEquals(EntityType.BUCKET, bucketTwoObj.getEntityType());
    Assert.assertEquals(0, bucketTwoObj.getNumTotalDir());
    Assert.assertEquals(2, bucketTwoObj.getNumTotalKey());

    // Test intermediate directory basics
    Response dirOneResponse = nsSummaryEndpoint.getBasicInfo(DIR_ONE_PATH);
    NamespaceSummaryResponse dirOneObj =
            (NamespaceSummaryResponse) dirOneResponse.getEntity();
    Assert.assertEquals(EntityType.DIRECTORY, dirOneObj.getEntityType());
    Assert.assertEquals(3, dirOneObj.getNumTotalDir());
    Assert.assertEquals(3, dirOneObj.getNumTotalKey());

    // Test invalid path
    Response invalidResponse = nsSummaryEndpoint.getBasicInfo(INVALID_PATH);
    NamespaceSummaryResponse invalidObj =
            (NamespaceSummaryResponse) invalidResponse.getEntity();
    Assert.assertEquals(ResponseStatus.PATH_NOT_FOUND,
            invalidObj.getStatus());

    // Test key
    Response keyResponse = nsSummaryEndpoint.getBasicInfo(KEY_PATH);
    NamespaceSummaryResponse keyResObj =
            (NamespaceSummaryResponse) keyResponse.getEntity();
    Assert.assertEquals(EntityType.KEY, keyResObj.getEntityType());
  }

}