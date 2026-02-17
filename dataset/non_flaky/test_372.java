class DummyClass_372 {
  @Test(timeout = 5000)
  public void testRenameSrcNotWritableFS() throws Exception {
    fs.setPermission(linkParent, new FsPermission((short) 0555));
    doRenameSrcNotWritableFS();
  }

}