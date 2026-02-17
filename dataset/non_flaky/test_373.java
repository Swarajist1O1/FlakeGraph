class DummyClass_373 {
  @Test
  public void testAclRenameSrcNotWritableFS() throws Exception {
    fs.setAcl(linkParent, Arrays.asList(
      aclEntry(ACCESS, USER, ALL),
      aclEntry(ACCESS, USER, user.getUserName(), READ_EXECUTE),
      aclEntry(ACCESS, GROUP, ALL),
      aclEntry(ACCESS, OTHER, ALL)));
    doRenameSrcNotWritableFS();
  }

}