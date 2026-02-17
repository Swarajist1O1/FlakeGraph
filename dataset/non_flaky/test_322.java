class DummyClass_322 {
  @Test
  public void testEviction() throws IOException {
    NfsConfiguration conf = new NfsConfiguration();
    conf.set(FileSystem.FS_DEFAULT_NAME_KEY, "hdfs://localhost");

    // Only one entry will be in the cache
    final int MAX_CACHE_SIZE = 1;

    DFSClientCache cache = new DFSClientCache(conf, MAX_CACHE_SIZE);

    int namenodeId = Nfs3Utils.getNamenodeId(conf);
    DFSClient c1 = cache.getDfsClient("test1", namenodeId);
    assertTrue(cache.getDfsClient("test1", namenodeId)
        .toString().contains("ugi=test1"));
    assertEquals(c1, cache.getDfsClient("test1", namenodeId));
    assertFalse(isDfsClientClose(c1));

    cache.getDfsClient("test2", namenodeId);
    assertTrue(isDfsClientClose(c1));
    assertTrue("cache size should be the max size or less",
        cache.getClientCache().size() <= MAX_CACHE_SIZE);
  }

}