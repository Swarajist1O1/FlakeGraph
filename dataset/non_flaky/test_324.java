class DummyClass_324 {
  @Test
  public void testGetUserGroupInformation() throws IOException {
    String userName = "user1";
    String currentUser = "currentUser";

    UserGroupInformation currentUserUgi = UserGroupInformation
            .createUserForTesting(currentUser, new String[0]);
    NfsConfiguration conf = new NfsConfiguration();
    conf.set(FileSystem.FS_DEFAULT_NAME_KEY, "hdfs://localhost");
    DFSClientCache cache = new DFSClientCache(conf);
    UserGroupInformation ugiResult
            = cache.getUserGroupInformation(userName, currentUserUgi);

    assertThat(ugiResult.getUserName(), is(userName));
    assertThat(ugiResult.getRealUser(), is(currentUserUgi));
    assertThat(
            ugiResult.getAuthenticationMethod(),
            is(UserGroupInformation.AuthenticationMethod.PROXY));
  }

}