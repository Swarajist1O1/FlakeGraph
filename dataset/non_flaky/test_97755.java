class DummyClass_97755 {
    @Test
    public void testBeforeTsExtension() throws Exception {
        final Settings settings = TestUtils.settings();

        settings.extensions.add(new Extension() {

            @Override
            public EmitterExtensionFeatures getFeatures() {
                return new EmitterExtensionFeatures();
            }

}