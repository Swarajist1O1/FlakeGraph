class DummyClass_367 {
  @Test
  public void testAclRenameTargetNotWritableFC() throws Exception {
    fs.setAcl(target, Arrays.asList(
      aclEntry(ACCESS, USER, ALL),
      aclEntry(ACCESS, USER, user.getUserName(), READ_EXECUTE),
      aclEntry(ACCESS, GROUP, ALL),
      aclEntry(ACCESS, OTHER, ALL)));
    fs.setAcl(targetParent, Arrays.asList(
      aclEntry(ACCESS, USER, ALL),
      aclEntry(ACCESS, USER, user.getUserName(), READ_EXECUTE),
      aclEntry(ACCESS, GROUP, ALL),
      aclEntry(ACCESS, OTHER, ALL)));
    doRenameLinkTargetNotWritableFC();
  }

}