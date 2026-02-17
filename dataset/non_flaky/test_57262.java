class DummyClass_57262 {
  @Test
  public void testAdminFilterOzoneAndReconAdmins() throws Exception {
    OzoneConfiguration conf = new OzoneConfiguration();
    conf.setStrings(OzoneConfigKeys.OZONE_ADMINISTRATORS, "ozone");
    conf.setStrings(ReconConfigKeys.OZONE_RECON_ADMINISTRATORS, "recon");
    testAdminFilterWithPrincipal(conf, "ozone", true);
    testAdminFilterWithPrincipal(conf, "recon", true);
    testAdminFilterWithPrincipal(conf, "reject", false);

    conf.setStrings(OzoneConfigKeys.OZONE_ADMINISTRATORS,
        OzoneConfigKeys.OZONE_ADMINISTRATORS_WILDCARD);
    conf.setStrings(ReconConfigKeys.OZONE_RECON_ADMINISTRATORS,
        OzoneConfigKeys.OZONE_ADMINISTRATORS_WILDCARD);
    testAdminFilterWithPrincipal(conf, "other", true);
  }

}