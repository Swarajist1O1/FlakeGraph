class DummyClass_57261 {
  @Test
  public void testAdminFilterReconAdminsOnly() throws Exception {
    OzoneConfiguration conf = new OzoneConfiguration();
    conf.setStrings(ReconConfigKeys.OZONE_RECON_ADMINISTRATORS, "recon");
    testAdminFilterWithPrincipal(conf, "recon", true);
    testAdminFilterWithPrincipal(conf, "reject", false);

    conf.setStrings(ReconConfigKeys.OZONE_RECON_ADMINISTRATORS,
        OzoneConfigKeys.OZONE_ADMINISTRATORS_WILDCARD);
    testAdminFilterWithPrincipal(conf, "other", true);
  }

}