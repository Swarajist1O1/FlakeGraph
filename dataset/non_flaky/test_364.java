class DummyClass_364 {
  @Test(timeout = 5000)
  public void testFileStatus() throws Exception {
    fs.setPermission(target, new FsPermission((short) 0000));
    doGetFileLinkStatusTargetNotReadable();
  }

}