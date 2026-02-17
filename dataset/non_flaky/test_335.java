class DummyClass_335 {
  @Test
  public void testEviction() throws IOException, InterruptedException {
    NfsConfiguration conf = new NfsConfiguration();

    // Only two entries will be in the cache
    conf.setInt(NfsConfigKeys.DFS_NFS_MAX_OPEN_FILES_KEY, 2);

    DFSClient dfsClient = Mockito.mock(DFSClient.class);
    Nfs3FileAttributes attr = new Nfs3FileAttributes();
    HdfsDataOutputStream fos = Mockito.mock(HdfsDataOutputStream.class);
    Mockito.when(fos.getPos()).thenReturn((long) 0);

    OpenFileCtx context1 = new OpenFileCtx(fos, attr, "/dumpFilePath",
        dfsClient, new ShellBasedIdMapping(new NfsConfiguration()));
    OpenFileCtx context2 = new OpenFileCtx(fos, attr, "/dumpFilePath",
        dfsClient, new ShellBasedIdMapping(new NfsConfiguration()));
    OpenFileCtx context3 = new OpenFileCtx(fos, attr, "/dumpFilePath",
        dfsClient, new ShellBasedIdMapping(new NfsConfiguration()));
    OpenFileCtx context4 = new OpenFileCtx(fos, attr, "/dumpFilePath",
        dfsClient, new ShellBasedIdMapping(new NfsConfiguration()));
    OpenFileCtx context5 = new OpenFileCtx(fos, attr, "/dumpFilePath",
        dfsClient, new ShellBasedIdMapping(new NfsConfiguration()));

    OpenFileCtxCache cache = new OpenFileCtxCache(conf, 10 * 60 * 100);

    boolean ret = cache.put(new FileHandle(1), context1);
    assertTrue(ret);
    Thread.sleep(1000);
    ret = cache.put(new FileHandle(2), context2);
    assertTrue(ret);
    ret = cache.put(new FileHandle(3), context3);
    assertFalse(ret);
    assertTrue(cache.size() == 2);

    // Wait for the oldest stream to be evict-able, insert again
    Thread.sleep(NfsConfigKeys.DFS_NFS_STREAM_TIMEOUT_MIN_DEFAULT);
    assertTrue(cache.size() == 2);

    ret = cache.put(new FileHandle(3), context3);
    assertTrue(ret);
    assertTrue(cache.size() == 2);
    assertTrue(cache.get(new FileHandle(1)) == null);

    // Test inactive entry is evicted immediately
    context3.setActiveStatusForTest(false);
    ret = cache.put(new FileHandle(4), context4);
    assertTrue(ret);

    // Now the cache has context2 and context4
    // Test eviction failure if all entries have pending work.
    context2.getPendingWritesForTest().put(new OffsetRange(0, 100),
        new WriteCtx(null, 0, 0, 0, null, null, null, 0, false, null));
    context4.getPendingCommitsForTest().put(new Long(100),
        new CommitCtx(0, null, 0, attr));
    Thread.sleep(NfsConfigKeys.DFS_NFS_STREAM_TIMEOUT_MIN_DEFAULT);
    ret = cache.put(new FileHandle(5), context5);
    assertFalse(ret);
  }

}