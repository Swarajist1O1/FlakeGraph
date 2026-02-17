class DummyClass_133900 {
    @Test
    public void testInstallNecessaryPlugins() {
        final RequestStatus status = api().installNecessaryPlugins("artifactory@2.2.1");
        assertNotNull(status);
        assertTrue(status.value());
        assertTrue(status.errors().isEmpty());
    }

}