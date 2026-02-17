class DummyClass_33896 {
    @Test
    public void testResourceByStringIdAndVersion() throws Exception {
        final Map<String, Object> headers = new HashMap<>();
        // parameter type is Class
        headers.put("CamelFhir.resource", Patient.class);
        // parameter type is Long
        headers.put("CamelFhir.stringId", patient.getIdElement().getIdPart());
        // parameter type is String
        headers.put("CamelFhir.version", patient.getIdElement().getVersionIdPart());

        Patient result = requestBodyAndHeaders("direct://RESOURCE_BY_STRING_ID_AND_VERSION", null, headers);

        assertValidResponse(result);
    }

}