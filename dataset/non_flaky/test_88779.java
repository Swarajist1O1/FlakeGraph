class DummyClass_88779 {
    @Test
    public void testAllBundlesActiveAndFeaturesInstalled() throws Exception {
        // Asssert all bundles except fragments are ACTIVE.
        for (Bundle b : bundleCtx.getBundles()) {
            System.out.println(String.format("Checking state of bundle [symbolicName=%s, state=%s]",
                b.getSymbolicName(), b.getState()));

            if (b.getHeaders().get(Constants.FRAGMENT_HOST) == null)
                assertTrue(b.getState() == Bundle.ACTIVE);
        }

        // Check that according to the FeaturesService, all Ignite features except ignite-log4j are installed.
        Feature[] features = featuresSvc.getFeatures(IGNITE_FEATURES_NAME_REGEX);

        assertNotNull(features);
        assertEquals(EXPECTED_FEATURES, features.length);

        for (Feature f : features) {
            if (IGNORED_FEATURES.contains(f.getName()))
                continue;

            boolean installed = featuresSvc.isInstalled(f);

            System.out.println(String.format("Checking if feature is installed [featureName=%s, installed=%s]",
                f.getName(), installed));

            assertTrue(installed);
            assertEquals(PROJECT_VERSION.replaceAll("-", "."), f.getVersion().replaceAll("-", "."));
        }
    }

}