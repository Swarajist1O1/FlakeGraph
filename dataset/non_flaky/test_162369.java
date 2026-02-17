class DummyClass_162369 {
    @Test(expected = IllegalStateException.class)
    public void testForMissingNames() {
        LicenseAcceptance.assertLicenseAccepted("c");
    }

}