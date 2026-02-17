class DummyClass_57267 {
  @Test
  public void testDiskUsageWithReplication() throws Exception {
    setUpMultiBlockKey();
    Response keyResponse = nsSummaryEndpoint.getDiskUsage(MULTI_BLOCK_KEY_PATH,
            false, true);
    DUResponse replicaDUResponse = (DUResponse) keyResponse.getEntity();
    Assert.assertEquals(ResponseStatus.OK, replicaDUResponse.getStatus());
    Assert.assertEquals(MULTI_BLOCK_KEY_SIZE_WITH_REPLICA,
            replicaDUResponse.getSizeWithReplica());
  }

}