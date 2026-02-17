class DummyClass_106636 {
  @Test
  public void statfs() throws Exception {
    ByteBuffer buffer = ByteBuffer.allocateDirect(4 * Constants.KB);
    buffer.clear();
    Statvfs stbuf = Statvfs.of(buffer);

    int blockSize = 4 * Constants.KB;
    int totalBlocks = 4;
    int freeBlocks = 3;

    BlockMasterClient blockMasterClient = PowerMockito.mock(BlockMasterClient.class);
    PowerMockito.mockStatic(BlockMasterClient.Factory.class);
    when(BlockMasterClient.Factory.create(any())).thenReturn(blockMasterClient);

    BlockMasterInfo blockMasterInfo = new BlockMasterInfo();
    blockMasterInfo.setCapacityBytes(totalBlocks * blockSize);
    blockMasterInfo.setFreeBytes(freeBlocks * blockSize);
    when(blockMasterClient.getBlockMasterInfo(any())).thenReturn(blockMasterInfo);

    assertEquals(0, mFuseFs.statfs("/", stbuf));

    assertEquals(blockSize, stbuf.f_bsize.intValue());
    assertEquals(blockSize, stbuf.f_frsize.intValue());
    assertEquals(totalBlocks, stbuf.f_blocks.longValue());
    assertEquals(freeBlocks, stbuf.f_bfree.longValue());
    assertEquals(freeBlocks, stbuf.f_bavail.longValue());

    assertEquals(AlluxioJniFuseFileSystem.UNKNOWN_INODES, stbuf.f_files.intValue());
    assertEquals(AlluxioJniFuseFileSystem.UNKNOWN_INODES, stbuf.f_ffree.intValue());
    assertEquals(AlluxioJniFuseFileSystem.UNKNOWN_INODES, stbuf.f_favail.intValue());
    assertEquals(AlluxioJniFuseFileSystem.MAX_NAME_LENGTH, stbuf.f_namemax.intValue());
  }

}