class DummyClass_33862 {
    @Test
    public void testConfigurationWithCustomFactory() throws Exception {
        FhirEndpoint endpoint = getMandatoryEndpoint(TEST_URI_CUSTOM_CLIENT_FACTORY, FhirEndpoint.class);
        IGenericClient client = endpoint.getClient();
        assertTrue(client instanceof CustomClient);
    }

}