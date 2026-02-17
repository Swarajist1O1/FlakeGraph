class DummyClass_57268 {
  @Test
  public void testQuotaUsage() throws Exception {
    // volume level quota usage
    Response volResponse = nsSummaryEndpoint.getQuotaUsage(VOL_PATH);
    QuotaUsageResponse quVolRes = (QuotaUsageResponse) volResponse.getEntity();
    Assert.assertEquals(VOL_QUOTA, quVolRes.getQuota());
    Assert.assertEquals(TOTAL_DATA_SIZE, quVolRes.getQuotaUsed());

    // bucket level quota usage
    Response bucketRes = nsSummaryEndpoint.getQuotaUsage(BUCKET_ONE_PATH);
    QuotaUsageResponse quBucketRes = (QuotaUsageResponse) bucketRes.getEntity();
    Assert.assertEquals(BUCKET_ONE_QUOTA, quBucketRes.getQuota());
    Assert.assertEquals(BUCKET_ONE_DATA_SIZE, quBucketRes.getQuotaUsed());

    Response bucketRes2 = nsSummaryEndpoint.getQuotaUsage(BUCKET_TWO_PATH);
    QuotaUsageResponse quBucketRes2 =
            (QuotaUsageResponse) bucketRes2.getEntity();
    Assert.assertEquals(BUCKET_TWO_QUOTA, quBucketRes2.getQuota());
    Assert.assertEquals(BUCKET_TWO_DATA_SIZE, quBucketRes2.getQuotaUsed());

    // other level not applicable
    Response naResponse1 = nsSummaryEndpoint.getQuotaUsage(DIR_ONE_PATH);
    QuotaUsageResponse quotaUsageResponse1 =
            (QuotaUsageResponse) naResponse1.getEntity();
    Assert.assertEquals(ResponseStatus.TYPE_NOT_APPLICABLE,
            quotaUsageResponse1.getResponseCode());

    Response naResponse2 = nsSummaryEndpoint.getQuotaUsage(KEY_PATH);
    QuotaUsageResponse quotaUsageResponse2 =
            (QuotaUsageResponse) naResponse2.getEntity();
    Assert.assertEquals(ResponseStatus.TYPE_NOT_APPLICABLE,
            quotaUsageResponse2.getResponseCode());

    // invalid path request
    Response invalidRes = nsSummaryEndpoint.getQuotaUsage(INVALID_PATH);
    QuotaUsageResponse invalidResObj =
            (QuotaUsageResponse) invalidRes.getEntity();
    Assert.assertEquals(ResponseStatus.PATH_NOT_FOUND,
            invalidResObj.getResponseCode());
  }

}