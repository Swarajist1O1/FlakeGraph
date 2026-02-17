class DummyClass_33897 {
    @Test
    public void testResourceByStringIdAndVersionWithResourceClass() throws Exception {
        final Map<String, Object> headers = new HashMap<>();
        // parameter type is Class
        headers.put("CamelFhir.resourceClass", "Patient");
        // parameter type is Long
        headers.put("CamelFhir.stringId", patient.getIdElement().getIdPart());
        // parameter type is String
        headers.put("CamelFhir.version", patient.getIdElement().getVersionIdPart());

        Patient result = requestBodyAndHeaders("direct://RESOURCE_BY_STRING_ID_AND_VERSION_AND_STRING_RESOURCE", null, headers);

        assertValidResponse(result);
    }

}