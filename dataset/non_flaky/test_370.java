class DummyClass_370 {
  @Test(timeout = 5000)
  public void testRenameLinkTargetNotWritableFS() throws Exception {
    fs.setPermission(target, new FsPermission((short) 0555));
    fs.setPermission(targetParent, new FsPermission((short) 0555));
    doRenameLinkTargetNotWritableFS();
  }

}