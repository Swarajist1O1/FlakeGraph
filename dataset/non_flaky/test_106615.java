class DummyClass_106615 {
  @Test
  public void chownWithoutValidUidAndGid() throws Exception {
    long uid = AlluxioJniFuseFileSystem.ID_NOT_SET_VALUE;
    long gid = AlluxioJniFuseFileSystem.ID_NOT_SET_VALUE;
    mFuseFs.chown("/foo/bar", uid, gid);
    verify(mFileSystem, never()).setAttribute(any());

    uid = AlluxioJniFuseFileSystem.ID_NOT_SET_VALUE_UNSIGNED;
    gid = AlluxioJniFuseFileSystem.ID_NOT_SET_VALUE_UNSIGNED;
    mFuseFs.chown("/foo/bar", uid, gid);
    verify(mFileSystem, never()).setAttribute(any());
  }

}