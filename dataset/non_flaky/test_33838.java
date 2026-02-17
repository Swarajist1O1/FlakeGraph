class DummyClass_33838 {
    @Test
    public void testConfiguration() throws Exception {
        FhirEndpoint endpoint = getMandatoryEndpoint(TEST_URI, FhirEndpoint.class);
        GenericClient client = (GenericClient) endpoint.getClient();
        FhirConfiguration configuration = endpoint.getConfiguration();
        assertEquals(this.componentConfiguration, configuration);
        assertEquals("http://localhost:8080/hapi-fhir-jpaserver-example/baseDstu3", client.getUrlBase());
        assertEquals(EncodingEnum.JSON, client.getEncoding());
        assertEquals(SummaryEnum.TEXT, client.getSummary());
        List<Object> interceptors = client.getInterceptorService().getAllRegisteredInterceptors();
        assertEquals(5, interceptors.size());

        long counter = context.adapt(ExtendedCamelContext.class).getBeanIntrospection().getInvokedCounter();
        assertEquals(0, counter, "Should not use reflection");
    }

}