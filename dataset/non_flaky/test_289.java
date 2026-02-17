class DummyClass_289 {
  @Test
  public void testCheckCommit() throws IOException {
    DFSClient dfsClient = Mockito.mock(DFSClient.class);
    Nfs3FileAttributes attr = new Nfs3FileAttributes();
    HdfsDataOutputStream fos = Mockito.mock(HdfsDataOutputStream.class);
    Mockito.when(fos.getPos()).thenReturn((long) 0);

    NfsConfiguration conf = new NfsConfiguration();
    conf.setBoolean(NfsConfigKeys.LARGE_FILE_UPLOAD, false);
    OpenFileCtx ctx = new OpenFileCtx(fos, attr, "/dumpFilePath", dfsClient,
        new ShellBasedIdMapping(conf), false, conf);

    COMMIT_STATUS ret;

    // Test inactive open file context
    ctx.setActiveStatusForTest(false);
    Channel ch = Mockito.mock(Channel.class);
    ret = ctx.checkCommit(dfsClient, 0, ch, 1, attr, false);
    Assert.assertTrue(ret == COMMIT_STATUS.COMMIT_INACTIVE_CTX);

    ctx.getPendingWritesForTest().put(new OffsetRange(5, 10),
        new WriteCtx(null, 0, 0, 0, null, null, null, 0, false, null));
    ret = ctx.checkCommit(dfsClient, 0, ch, 1, attr, false);
    Assert.assertTrue(ret == COMMIT_STATUS.COMMIT_INACTIVE_WITH_PENDING_WRITE);

    // Test request with non zero commit offset
    ctx.setActiveStatusForTest(true);
    Mockito.when(fos.getPos()).thenReturn((long) 10);
    ctx.setNextOffsetForTest(10);
    COMMIT_STATUS status = ctx.checkCommitInternal(5, null, 1, attr, false);
    Assert.assertTrue(status == COMMIT_STATUS.COMMIT_DO_SYNC);
    // Do_SYNC state will be updated to FINISHED after data sync
    ret = ctx.checkCommit(dfsClient, 5, ch, 1, attr, false);
    Assert.assertTrue(ret == COMMIT_STATUS.COMMIT_FINISHED);
    
    status = ctx.checkCommitInternal(10, ch, 1, attr, false);
    Assert.assertTrue(status == COMMIT_STATUS.COMMIT_DO_SYNC);
    ret = ctx.checkCommit(dfsClient, 10, ch, 1, attr, false);
    Assert.assertTrue(ret == COMMIT_STATUS.COMMIT_FINISHED);

    ConcurrentNavigableMap<Long, CommitCtx> commits = ctx
        .getPendingCommitsForTest();
    Assert.assertTrue(commits.size() == 0);
    ret = ctx.checkCommit(dfsClient, 11, ch, 1, attr, false);
    Assert.assertTrue(ret == COMMIT_STATUS.COMMIT_WAIT);
    Assert.assertTrue(commits.size() == 1);
    long key = commits.firstKey();
    Assert.assertTrue(key == 11);

    // Test request with zero commit offset
    commits.remove(new Long(11));
    // There is one pending write [5,10]
    ret = ctx.checkCommit(dfsClient, 0, ch, 1, attr, false);
    Assert.assertTrue(ret == COMMIT_STATUS.COMMIT_WAIT);
    Assert.assertTrue(commits.size() == 1);
    key = commits.firstKey();
    Assert.assertTrue(key == 9);

    // Empty pending writes
    ctx.getPendingWritesForTest().remove(new OffsetRange(5, 10));
    ret = ctx.checkCommit(dfsClient, 0, ch, 1, attr, false);
    Assert.assertTrue(ret == COMMIT_STATUS.COMMIT_FINISHED);
  }

}