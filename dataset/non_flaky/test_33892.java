class DummyClass_33892 {
    @Test
    public void testResourceByStringId() throws Exception {
        final Map<String, Object> headers = new HashMap<>();
        // parameter type is Class
        headers.put("CamelFhir.resource", Patient.class);
        // parameter type is Long
        headers.put("CamelFhir.stringId", patient.getIdElement().getIdPart());

        Patient result = requestBodyAndHeaders("direct://RESOURCE_BY_STRING_ID", null, headers);

        assertValidResponse(result);
    }

}