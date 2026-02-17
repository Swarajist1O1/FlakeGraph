class DummyClass_106608 {
  @Test
  public void offset() {
    // allocate an enough large memory for jnistat
    FileStat jnistat = FileStat.of(ByteBuffer.allocate(256));
    ru.serce.jnrfuse.struct.FileStat jnrstat =
        new ru.serce.jnrfuse.struct.FileStat(Runtime.getSystemRuntime());

    assertEquals(jnrstat.st_dev.offset(), jnistat.st_dev.offset());
    assertEquals(jnrstat.st_ino.offset(), jnistat.st_ino.offset());
    assertEquals(jnrstat.st_nlink.offset(), jnistat.st_nlink.offset());
    assertEquals(jnrstat.st_mode.offset(), jnistat.st_mode.offset());
    assertEquals(jnrstat.st_uid.offset(), jnistat.st_uid.offset());
    assertEquals(jnrstat.st_gid.offset(), jnistat.st_gid.offset());
    assertEquals(jnrstat.st_rdev.offset(), jnistat.st_rdev.offset());
    assertEquals(jnrstat.st_size.offset(), jnistat.st_size.offset());
    assertEquals(jnrstat.st_blksize.offset(), jnistat.st_blksize.offset());
    assertEquals(jnrstat.st_blocks.offset(), jnistat.st_blocks.offset());
    assertEquals(jnrstat.st_atim.tv_sec.offset(), jnistat.st_atim.tv_sec.offset());
    assertEquals(jnrstat.st_atim.tv_nsec.offset(), jnistat.st_atim.tv_nsec.offset());
    assertEquals(jnrstat.st_mtim.tv_sec.offset(), jnistat.st_mtim.tv_sec.offset());
    assertEquals(jnrstat.st_mtim.tv_nsec.offset(), jnistat.st_mtim.tv_nsec.offset());
    assertEquals(jnrstat.st_ctim.tv_sec.offset(), jnistat.st_ctim.tv_sec.offset());
    assertEquals(jnrstat.st_ctim.tv_nsec.offset(), jnistat.st_ctim.tv_nsec.offset());
  }

}