class DummyClass_323 {
  @Test
  public void testGetUserGroupInformationSecure() throws IOException {
    String userName = "user1";
    String currentUser = "test-user";


    NfsConfiguration conf = new NfsConfiguration();
    conf.set(FileSystem.FS_DEFAULT_NAME_KEY, "hdfs://localhost");
    UserGroupInformation currentUserUgi
            = UserGroupInformation.createRemoteUser(currentUser);
    currentUserUgi.setAuthenticationMethod(KERBEROS);
    UserGroupInformation.setLoginUser(currentUserUgi);

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