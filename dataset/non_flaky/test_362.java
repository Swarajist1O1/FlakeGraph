class DummyClass_362 {
  @Test(timeout = 5000)
  public void testReadWhenTargetNotReadable() throws Exception {
    fs.setPermission(target, new FsPermission((short) 0000));
    doReadTargetNotReadable();
  }

}