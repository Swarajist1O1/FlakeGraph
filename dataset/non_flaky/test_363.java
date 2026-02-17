class DummyClass_363 {
  @Test
  public void testAclReadTargetNotReadable() throws Exception {
    fs.setAcl(target, Arrays.asList(
      aclEntry(ACCESS, USER, READ_WRITE),
      aclEntry(ACCESS, USER, user.getUserName(), NONE),
      aclEntry(ACCESS, GROUP, READ),
      aclEntry(ACCESS, OTHER, READ)));
    doReadTargetNotReadable();
  }

}