class DummyClass_33901 {
    @Test
    public void testResourceByUrlAndStringResource() throws Exception {
        final Map<String, Object> headers = new HashMap<>();
        // parameter type is String
        headers.put("CamelFhir.resourceClass", "Patient");
        // parameter type is String
        headers.put("CamelFhir.url", this.patient.getId());

        Patient result = requestBodyAndHeaders("direct://RESOURCE_BY_URL_AND_STRING_RESOURCE", null, headers);

        assertValidResponse(result);
    }

}