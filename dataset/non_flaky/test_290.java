class DummyClass_290 {
  @Test
  public void testCheckCommitLargeFileUpload() throws IOException {
    DFSClient dfsClient = Mockito.mock(DFSClient.class);
    Nfs3FileAttributes attr = new Nfs3FileAttributes();
    HdfsDataOutputStream fos = Mockito.mock(HdfsDataOutputStream.class);
    Mockito.when(fos.getPos()).thenReturn((long) 0);

    NfsConfiguration conf = new NfsConfiguration();
    conf.setBoolean(NfsConfigKeys.LARGE_FILE_UPLOAD, true);
    OpenFileCtx ctx = new OpenFileCtx(fos, attr, "/dumpFilePath", dfsClient,
        new ShellBasedIdMapping(conf), false, conf);

    COMMIT_STATUS ret;

    // Test inactive open file context
    ctx.setActiveStatusForTest(false);
    Channel ch = Mockito.mock(Channel.class);
    ret = ctx.checkCommit(dfsClient, 0, ch, 1, attr, false);
    Assert.assertTrue(ret == COMMIT_STATUS.COMMIT_INACTIVE_CTX);

    ctx.getPendingWritesForTest().put(new OffsetRange(10, 15),
        new WriteCtx(null, 0, 0, 0, null, null, null, 0, false, null));
    ret = ctx.checkCommit(dfsClient, 0, ch, 1, attr, false);
    Assert.assertTrue(ret == COMMIT_STATUS.COMMIT_INACTIVE_WITH_PENDING_WRITE);

    // Test request with non zero commit offset
    ctx.setActiveStatusForTest(true);
    Mockito.when(fos.getPos()).thenReturn((long) 8);
    ctx.setNextOffsetForTest(10);
    COMMIT_STATUS status = ctx.checkCommitInternal(5, null, 1, attr, false);
    Assert.assertTrue(status == COMMIT_STATUS.COMMIT_DO_SYNC);
    // Do_SYNC state will be updated to FINISHED after data sync
    ret = ctx.checkCommit(dfsClient, 5, ch, 1, attr, false);
    Assert.assertTrue(ret == COMMIT_STATUS.COMMIT_FINISHED);
    
    // Test commit sequential writes
    status = ctx.checkCommitInternal(10, ch, 1, attr, false);
    Assert.assertTrue(status == COMMIT_STATUS.COMMIT_SPECIAL_WAIT);
    ret = ctx.checkCommit(dfsClient, 10, ch, 1, attr, false);
    Assert.assertTrue(ret == COMMIT_STATUS.COMMIT_SPECIAL_WAIT);

    // Test commit non-sequential writes
    ConcurrentNavigableMap<Long, CommitCtx> commits = ctx
        .getPendingCommitsForTest();
    Assert.assertTrue(commits.size() == 1);
    ret = ctx.checkCommit(dfsClient, 16, ch, 1, attr, false);
    Assert.assertTrue(ret == COMMIT_STATUS.COMMIT_SPECIAL_SUCCESS);
    Assert.assertTrue(commits.size() == 1);
    
    // Test request with zero commit offset
    commits.remove(new Long(10));
    // There is one pending write [10,15]
    ret = ctx.checkCommitInternal(0, ch, 1, attr, false);
    Assert.assertTrue(ret == COMMIT_STATUS.COMMIT_SPECIAL_WAIT);
    
    ret = ctx.checkCommitInternal(9, ch, 1, attr, false);
    Assert.assertTrue(ret == COMMIT_STATUS.COMMIT_SPECIAL_WAIT);
    Assert.assertTrue(commits.size() == 2);

    // Empty pending writes. nextOffset=10, flushed pos=8
    ctx.getPendingWritesForTest().remove(new OffsetRange(10, 15));
    ret = ctx.checkCommit(dfsClient, 0, ch, 1, attr, false);
    Assert.assertTrue(ret == COMMIT_STATUS.COMMIT_SPECIAL_WAIT);
    
    // Empty pending writes
    ctx.setNextOffsetForTest((long) 8); // flushed pos = 8
    ret = ctx.checkCommit(dfsClient, 0, ch, 1, attr, false);
    Assert.assertTrue(ret == COMMIT_STATUS.COMMIT_FINISHED);
    
  }

}