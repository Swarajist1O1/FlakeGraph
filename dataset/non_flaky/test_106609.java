class DummyClass_106609 {
  @Test
  public void dataConsistency() {
    FileStat stat = FileStat.of(ByteBuffer.allocateDirect(256));
    int mode = FileStat.ALL_READ | FileStat.ALL_WRITE | FileStat.S_IFDIR;
    long size = 0x123456789888721L;
    stat.st_mode.set(mode);
    stat.st_size.set(size);
    assertEquals(mode, stat.st_mode.intValue());
    assertEquals(size, stat.st_size.longValue());

    ByteBuffer buf = stat.getBuffer();
    assertEquals(mode, buf.getShort((int) stat.st_mode.offset()));
    assertEquals(size, buf.getLong((int) stat.st_size.offset()));
  }

}