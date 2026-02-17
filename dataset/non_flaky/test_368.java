class DummyClass_368 {
  @Test(timeout = 5000)
  public void testRenameSrcNotWritableFC() throws Exception {
    fs.setPermission(linkParent, new FsPermission((short) 0555));
    doRenameSrcNotWritableFC();
  }

}