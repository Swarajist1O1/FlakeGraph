class DummyClass_33891 {
    @Test
    public void testResourceByLongId() throws Exception {
        final Map<String, Object> headers = new HashMap<>();
        // parameter type is Class
        headers.put("CamelFhir.resource", Patient.class);
        // parameter type is Long
        headers.put("CamelFhir.longId", Long.valueOf(patient.getIdElement().getIdPart()));

        Patient result = requestBodyAndHeaders("direct://RESOURCE_BY_LONG_ID", null, headers);

        assertValidResponse(result);
    }

}