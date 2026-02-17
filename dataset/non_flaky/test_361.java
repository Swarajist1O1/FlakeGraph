class DummyClass_361 {
  @Test
  public void testAclDelete() throws Exception {
    fs.setAcl(linkParent, Arrays.asList(
      aclEntry(ACCESS, USER, ALL),
      aclEntry(ACCESS, USER, user.getUserName(), READ_EXECUTE),
      aclEntry(ACCESS, GROUP, ALL),
      aclEntry(ACCESS, OTHER, ALL)));
    doDeleteLinkParentNotWritable();

    fs.setAcl(linkParent, Arrays.asList(
      aclEntry(ACCESS, USER, ALL),
      aclEntry(ACCESS, GROUP, ALL),
      aclEntry(ACCESS, OTHER, ALL)));
    fs.setAcl(targetParent, Arrays.asList(
      aclEntry(ACCESS, USER, ALL),
      aclEntry(ACCESS, USER, user.getUserName(), READ_EXECUTE),
      aclEntry(ACCESS, GROUP, ALL),
      aclEntry(ACCESS, OTHER, ALL)));
    fs.setAcl(target, Arrays.asList(
      aclEntry(ACCESS, USER, ALL),
      aclEntry(ACCESS, USER, user.getUserName(), READ_EXECUTE),
      aclEntry(ACCESS, GROUP, ALL),
      aclEntry(ACCESS, OTHER, ALL)));
    doDeleteTargetParentAndTargetNotWritable();
  }

}