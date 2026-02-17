class DummyClass_106613 {
  @Test
  public void chownWithoutValidGid() throws Exception {
    long uid = AlluxioFuseUtils.getUid(System.getProperty("user.name"));
    long gid = AlluxioJniFuseFileSystem.ID_NOT_SET_VALUE;
    mFuseFs.chown("/foo/bar", uid, gid);
    String userName = System.getProperty("user.name");
    String groupName = AlluxioFuseUtils.getGroupName(userName);
    AlluxioURI expectedPath = BASE_EXPECTED_URI.join("/foo/bar");
    SetAttributePOptions options =
        SetAttributePOptions.newBuilder().setGroup(groupName).setOwner(userName).build();
    verify(mFileSystem).setAttribute(expectedPath, options);

    gid = AlluxioJniFuseFileSystem.ID_NOT_SET_VALUE_UNSIGNED;
    mFuseFs.chown("/foo/bar", uid, gid);
    verify(mFileSystem, times(2)).setAttribute(expectedPath, options);
  }

}