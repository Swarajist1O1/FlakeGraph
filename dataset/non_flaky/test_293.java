class DummyClass_293 {
  @Test
  public void testCheckCommitFromReadLargeFileUpload() throws IOException {
    DFSClient dfsClient = Mockito.mock(DFSClient.class);
    Nfs3FileAttributes attr = new Nfs3FileAttributes();
    HdfsDataOutputStream fos = Mockito.mock(HdfsDataOutputStream.class);
    Mockito.when(fos.getPos()).thenReturn((long) 0);
    NfsConfiguration config = new NfsConfiguration();

    config.setBoolean(NfsConfigKeys.LARGE_FILE_UPLOAD, true);
    OpenFileCtx ctx = new OpenFileCtx(fos, attr, "/dumpFilePath", dfsClient,
        new ShellBasedIdMapping(config), false, config);

    FileHandle h = new FileHandle(1); // fake handle for "/dumpFilePath"
    COMMIT_STATUS ret;
    WriteManager wm = new WriteManager(new ShellBasedIdMapping(config), config, false);
    assertTrue(wm.addOpenFileStream(h, ctx));
    
    // Test inactive open file context
    ctx.setActiveStatusForTest(false);
    Channel ch = Mockito.mock(Channel.class);
    ret = ctx.checkCommit(dfsClient, 0, ch, 1, attr, true);
    assertEquals( COMMIT_STATUS.COMMIT_INACTIVE_CTX, ret);
    assertEquals(Nfs3Status.NFS3_OK, wm.commitBeforeRead(dfsClient, h, 0));
    
    ctx.getPendingWritesForTest().put(new OffsetRange(10, 15),
        new WriteCtx(null, 0, 0, 0, null, null, null, 0, false, null));
    ret = ctx.checkCommit(dfsClient, 0, ch, 1, attr, true);
    assertEquals(COMMIT_STATUS.COMMIT_INACTIVE_WITH_PENDING_WRITE, ret);
    assertEquals(Nfs3Status.NFS3ERR_IO, wm.commitBeforeRead(dfsClient, h, 0));
    
    // Test request with non zero commit offset
    ctx.setActiveStatusForTest(true);
    Mockito.when(fos.getPos()).thenReturn((long) 6);
    ctx.setNextOffsetForTest((long)10);
    COMMIT_STATUS status = ctx.checkCommitInternal(5, ch, 1, attr, false);
    assertEquals(COMMIT_STATUS.COMMIT_DO_SYNC, status);
    // Do_SYNC state will be updated to FINISHED after data sync
    ret = ctx.checkCommit(dfsClient, 5, ch, 1, attr, true);
    assertEquals(COMMIT_STATUS.COMMIT_FINISHED, ret);
    assertEquals(Nfs3Status.NFS3_OK, wm.commitBeforeRead(dfsClient, h, 5));
 
    // Test request with sequential writes
    status = ctx.checkCommitInternal(9, ch, 1, attr, true);
    assertTrue(status == COMMIT_STATUS.COMMIT_SPECIAL_WAIT);
    ret = ctx.checkCommit(dfsClient, 9, ch, 1, attr, true);
    assertEquals(COMMIT_STATUS.COMMIT_SPECIAL_WAIT, ret);
    assertEquals(Nfs3Status.NFS3ERR_JUKEBOX, wm.commitBeforeRead(dfsClient, h, 9));

    // Test request with non-sequential writes
    ConcurrentNavigableMap<Long, CommitCtx> commits = ctx
        .getPendingCommitsForTest();
    assertTrue(commits.size() == 0);
    ret = ctx.checkCommit(dfsClient, 16, ch, 1, attr, true);
    assertEquals(COMMIT_STATUS.COMMIT_SPECIAL_SUCCESS, ret);
    assertEquals(0, commits.size()); // commit triggered by read doesn't wait
    assertEquals(Nfs3Status.NFS3_OK, wm.commitBeforeRead(dfsClient, h, 16));

    // Test request with zero commit offset
    // There is one pending write [10,15]
    ret = ctx.checkCommit(dfsClient, 0, ch, 1, attr, true);
    assertEquals(COMMIT_STATUS.COMMIT_SPECIAL_WAIT, ret);
    assertEquals(0, commits.size());
    assertEquals(Nfs3Status.NFS3ERR_JUKEBOX, wm.commitBeforeRead(dfsClient, h, 0));

    // Empty pending writes
    ctx.getPendingWritesForTest().remove(new OffsetRange(10, 15));
    ret = ctx.checkCommit(dfsClient, 0, ch, 1, attr, true);
    assertEquals(COMMIT_STATUS.COMMIT_SPECIAL_WAIT, ret);
    assertEquals(Nfs3Status.NFS3ERR_JUKEBOX, wm.commitBeforeRead(dfsClient, h, 0));
  }

}