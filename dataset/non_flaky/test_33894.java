class DummyClass_33894 {
    @Test
    public void testResourceByLongIdAndStringResource() throws Exception {
        final Map<String, Object> headers = new HashMap<>();
        // parameter type is Class
        headers.put("CamelFhir.resource", Patient.class);
        // parameter type is Long
        headers.put("CamelFhir.longId", Long.valueOf(patient.getIdElement().getIdPart()));

        Patient result = requestBodyAndHeaders("direct://RESOURCE_BY_LONG_ID_AND_STRING_RESOURCE", null, headers);

        assertValidResponse(result);
    }

}