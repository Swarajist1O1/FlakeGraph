class DummyClass_336 {
  @Test
  public void testScan() throws IOException, InterruptedException {
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

    OpenFileCtxCache cache = new OpenFileCtxCache(conf, 10 * 60 * 100);

    // Test cleaning expired entry
    boolean ret = cache.put(new FileHandle(1), context1);
    assertTrue(ret);
    ret = cache.put(new FileHandle(2), context2);
    assertTrue(ret);
    Thread.sleep(NfsConfigKeys.DFS_NFS_STREAM_TIMEOUT_MIN_DEFAULT + 1);
    cache.scan(NfsConfigKeys.DFS_NFS_STREAM_TIMEOUT_MIN_DEFAULT);
    assertTrue(cache.size() == 0);

    // Test cleaning inactive entry
    ret = cache.put(new FileHandle(3), context3);
    assertTrue(ret);
    ret = cache.put(new FileHandle(4), context4);
    assertTrue(ret);
    context3.setActiveStatusForTest(false);
    cache.scan(NfsConfigKeys.DFS_NFS_STREAM_TIMEOUT_DEFAULT);
    assertTrue(cache.size() == 1);
    assertTrue(cache.get(new FileHandle(3)) == null);
    assertTrue(cache.get(new FileHandle(4)) != null);
  }

}