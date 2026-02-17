class DummyClass_33899 {
    @Test
    public void testResourceByUrl() throws Exception {
        final Map<String, Object> headers = new HashMap<>();
        // parameter type is Class
        headers.put("CamelFhir.resource", Patient.class);
        // parameter type is String
        headers.put("CamelFhir.url", this.patient.getId());

        Patient result = requestBodyAndHeaders("direct://RESOURCE_BY_URL", null, headers);

        assertValidResponse(result);
    }

}