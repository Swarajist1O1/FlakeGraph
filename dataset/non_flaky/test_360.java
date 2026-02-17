class DummyClass_360 {
  @Test(timeout = 5000)
  public void testDelete() throws Exception {
    fs.setPermission(linkParent, new FsPermission((short) 0555));
    doDeleteLinkParentNotWritable();

    fs.setPermission(linkParent, new FsPermission((short) 0777));
    fs.setPermission(targetParent, new FsPermission((short) 0555));
    fs.setPermission(target, new FsPermission((short) 0555));
    doDeleteTargetParentAndTargetNotWritable();
  }

}