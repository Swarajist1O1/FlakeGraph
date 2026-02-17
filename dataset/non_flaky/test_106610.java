class DummyClass_106610 {
  @Test
  public void offset() {
    FuseFileInfo jnifi = FuseFileInfo.of(ByteBuffer.allocate(256));
    ru.serce.jnrfuse.struct.FuseFileInfo jnrfi =
        ru.serce.jnrfuse.struct.FuseFileInfo.of(Pointer.wrap(Runtime.getSystemRuntime(), 0x0));
    assertEquals(jnrfi.flags.offset(), jnifi.flags.offset());
    assertEquals(jnrfi.fh.offset(), jnifi.fh.offset());
  }

}