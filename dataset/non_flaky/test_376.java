class DummyClass_376 {
  @Test
  public void testRefreshSuperUserGroupsConfiguration() throws Exception {
    final String SUPER_USER = "super_user";
    final List<String> groupNames1 = new ArrayList<>();
    groupNames1.add("gr1");
    groupNames1.add("gr2");
    final List<String> groupNames2 = new ArrayList<>();
    groupNames2.add("gr3");
    groupNames2.add("gr4");

    //keys in conf
    String userKeyGroups = DefaultImpersonationProvider.getTestProvider().
        getProxySuperuserGroupConfKey(SUPER_USER);
    String userKeyHosts = DefaultImpersonationProvider.getTestProvider().
        getProxySuperuserIpConfKey (SUPER_USER);
    
    config.set(userKeyGroups, "gr3,gr4,gr5"); // superuser can proxy for this group
    config.set(userKeyHosts,"127.0.0.1");
    ProxyUsers.refreshSuperUserGroupsConfiguration(config);
    
    UserGroupInformation ugi1 = mock(UserGroupInformation.class);
    UserGroupInformation ugi2 = mock(UserGroupInformation.class);
    UserGroupInformation suUgi = mock(UserGroupInformation.class);
    when(ugi1.getRealUser()).thenReturn(suUgi);
    when(ugi2.getRealUser()).thenReturn(suUgi);

    when(suUgi.getShortUserName()).thenReturn(SUPER_USER); // super user
    when(suUgi.getUserName()).thenReturn(SUPER_USER+"L"); // super user
     
    when(ugi1.getShortUserName()).thenReturn("user1");
    when(ugi2.getShortUserName()).thenReturn("user2");
    
    when(ugi1.getUserName()).thenReturn("userL1");
    when(ugi2.getUserName()).thenReturn("userL2");

    // set groups for users
    when(ugi1.getGroups()).thenReturn(groupNames1);
    when(ugi2.getGroups()).thenReturn(groupNames2);


    // check before
    try {
      ProxyUsers.authorize(ugi1, "127.0.0.1");
      fail("first auth for " + ugi1.getShortUserName() + " should've failed ");
    } catch (AuthorizationException e) {
      // expected
      System.err.println("auth for " + ugi1.getUserName() + " failed");
    }
    try {
      ProxyUsers.authorize(ugi2, "127.0.0.1");
      System.err.println("auth for " + ugi2.getUserName() + " succeeded");
      // expected
    } catch (AuthorizationException e) {
      fail("first auth for " + ugi2.getShortUserName() + " should've succeeded: " + e.getLocalizedMessage());
    }
    
    // refresh will look at configuration on the server side
    // add additional resource with the new value
    // so the server side will pick it up
    String rsrc = "testGroupMappingRefresh_rsrc.xml";
    addNewConfigResource(rsrc, userKeyGroups, "gr2", userKeyHosts, "127.0.0.1");  
    
    DFSAdmin admin = new DFSAdmin(config);
    String [] args = new String[]{"-refreshSuperUserGroupsConfiguration"};
    admin.run(args);
    
    try {
      ProxyUsers.authorize(ugi2, "127.0.0.1");
      fail("second auth for " + ugi2.getShortUserName() + " should've failed ");
    } catch (AuthorizationException e) {
      // expected
      System.err.println("auth for " + ugi2.getUserName() + " failed");
    }
    try {
      ProxyUsers.authorize(ugi1, "127.0.0.1");
      System.err.println("auth for " + ugi1.getUserName() + " succeeded");
      // expected
    } catch (AuthorizationException e) {
      fail("second auth for " + ugi1.getShortUserName() + " should've succeeded: " + e.getLocalizedMessage());
    }
    
    
  }

}