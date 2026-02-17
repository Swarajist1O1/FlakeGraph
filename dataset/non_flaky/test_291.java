class DummyClass_291 {
  @Test
  public void testCheckCommitAixCompatMode() throws IOException {
    DFSClient dfsClient = Mockito.mock(DFSClient.class);
    Nfs3FileAttributes attr = new Nfs3FileAttributes();
    HdfsDataOutputStream fos = Mockito.mock(HdfsDataOutputStream.class);

    NfsConfiguration conf = new NfsConfiguration();
    conf.setBoolean(NfsConfigKeys.LARGE_FILE_UPLOAD, false);
    // Enable AIX compatibility mode.
    OpenFileCtx ctx = new OpenFileCtx(fos, attr, "/dumpFilePath", dfsClient,
        new ShellBasedIdMapping(new NfsConfiguration()), true, conf);
    
    // Test fall-through to pendingWrites check in the event that commitOffset
    // is greater than the number of bytes we've so far flushed.
    Mockito.when(fos.getPos()).thenReturn((long) 2);
    COMMIT_STATUS status = ctx.checkCommitInternal(5, null, 1, attr, false);
    Assert.assertTrue(status == COMMIT_STATUS.COMMIT_FINISHED);
    
    // Test the case when we actually have received more bytes than we're trying
    // to commit.
    ctx.getPendingWritesForTest().put(new OffsetRange(0, 10),
        new WriteCtx(null, 0, 0, 0, null, null, null, 0, false, null));
    Mockito.when(fos.getPos()).thenReturn((long) 10);
    ctx.setNextOffsetForTest((long)10);
    status = ctx.checkCommitInternal(5, null, 1, attr, false);
    Assert.assertTrue(status == COMMIT_STATUS.COMMIT_DO_SYNC);
  }

}