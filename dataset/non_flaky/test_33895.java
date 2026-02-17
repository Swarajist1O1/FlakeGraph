class DummyClass_33895 {
    @Test
    public void testResourceByStringIdAndStringResource() throws Exception {
        final Map<String, Object> headers = new HashMap<>();
        // parameter type is Class
        headers.put("CamelFhir.resource", Patient.class);
        // parameter type is Long
        headers.put("CamelFhir.stringId", patient.getIdElement().getIdPart());

        Patient result = requestBodyAndHeaders("direct://RESOURCE_BY_STRING_ID_AND_STRING_RESOURCE", null, headers);

        assertValidResponse(result);
    }

}