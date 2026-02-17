class DummyClass_297 {
  @Test
  public void testCheckSequential() throws IOException {
    DFSClient dfsClient = Mockito.mock(DFSClient.class);
    Nfs3FileAttributes attr = new Nfs3FileAttributes();
    HdfsDataOutputStream fos = Mockito.mock(HdfsDataOutputStream.class);
    Mockito.when(fos.getPos()).thenReturn((long) 0);
    NfsConfiguration config = new NfsConfiguration();

    config.setBoolean(NfsConfigKeys.LARGE_FILE_UPLOAD, false);
    OpenFileCtx ctx = new OpenFileCtx(fos, attr, "/dumpFilePath", dfsClient,
        new ShellBasedIdMapping(config), false, config);
    
    ctx.getPendingWritesForTest().put(new OffsetRange(5, 10),
        new WriteCtx(null, 0, 0, 0, null, null, null, 0, false, null));
    ctx.getPendingWritesForTest().put(new OffsetRange(10, 15),
        new WriteCtx(null, 0, 0, 0, null, null, null, 0, false, null));
    ctx.getPendingWritesForTest().put(new OffsetRange(20, 25),
        new WriteCtx(null, 0, 0, 0, null, null, null, 0, false, null));

    assertTrue(!ctx.checkSequential(5, 4));
    assertTrue(ctx.checkSequential(9, 5));
    assertTrue(ctx.checkSequential(10, 5));
    assertTrue(ctx.checkSequential(14, 5));
    assertTrue(!ctx.checkSequential(15, 5));
    assertTrue(!ctx.checkSequential(20, 5));
    assertTrue(!ctx.checkSequential(25, 5));
    assertTrue(!ctx.checkSequential(999, 5));
  }

}