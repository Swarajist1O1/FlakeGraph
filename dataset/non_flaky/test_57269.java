class DummyClass_57269 {
  @Test
  public void testFileSizeDist() throws Exception {
    Response volRes = nsSummaryEndpoint.getFileSizeDistribution(VOL_PATH);
    FileSizeDistributionResponse volFileSizeDistResObj =
            (FileSizeDistributionResponse) volRes.getEntity();
    // If the volume has the correct file size distribution,
    // other lower level should be correct as well, given all
    // other previous tests have passed.
    int[] volFileSizeDist = volFileSizeDistResObj.getFileSizeDist();
    for (int i = 0; i < ReconConstants.NUM_OF_BINS; ++i) {
      if (i == 0 || i == 2) {
        Assert.assertEquals(2, volFileSizeDist[i]);
      } else if (i == 1 || i == 3) {
        Assert.assertEquals(1, volFileSizeDist[i]);
      } else {
        Assert.assertEquals(0, volFileSizeDist[i]);
      }
    }
  }

}