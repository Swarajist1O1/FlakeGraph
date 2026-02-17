class DummyClass_366 {
  @Test(timeout = 5000)
  public void testRenameLinkTargetNotWritableFC() throws Exception {
    fs.setPermission(target, new FsPermission((short) 0555));
    fs.setPermission(targetParent, new FsPermission((short) 0555));
    doRenameLinkTargetNotWritableFC();
  }

}