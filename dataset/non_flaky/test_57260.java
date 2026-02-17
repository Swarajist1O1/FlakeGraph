class DummyClass_57260 {
  @Test
  public void testAdminFilterOzoneAdminsOnly() throws Exception {
    OzoneConfiguration conf = new OzoneConfiguration();
    conf.setStrings(OzoneConfigKeys.OZONE_ADMINISTRATORS, "ozone");
    testAdminFilterWithPrincipal(conf, "ozone", true);
    testAdminFilterWithPrincipal(conf, "reject", false);

    conf.setStrings(OzoneConfigKeys.OZONE_ADMINISTRATORS,
        OzoneConfigKeys.OZONE_ADMINISTRATORS_WILDCARD);
    testAdminFilterWithPrincipal(conf, "other", true);
  }

}