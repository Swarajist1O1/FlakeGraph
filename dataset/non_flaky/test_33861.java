class DummyClass_33861 {
    @Test
    public void testConfigurationWithCustomClient() throws Exception {
        FhirEndpoint endpoint = getMandatoryEndpoint(TEST_URI_CUSTOM_CLIENT, FhirEndpoint.class);
        IGenericClient client = endpoint.getClient();
        assertTrue(client instanceof CustomClient);
    }

}