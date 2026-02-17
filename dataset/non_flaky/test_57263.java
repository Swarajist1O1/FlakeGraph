class DummyClass_57263 {
  @Test
  public void testAdminFilterNoAdmins() throws Exception {
    testAdminFilterWithPrincipal(new OzoneConfiguration(), "reject", false);
  }

}