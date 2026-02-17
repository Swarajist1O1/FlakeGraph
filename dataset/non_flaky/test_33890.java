class DummyClass_33890 {
    @Test
    public void testResourceById() throws Exception {
        final Map<String, Object> headers = new HashMap<>();
        // parameter type is Class
        headers.put("CamelFhir.resource", Patient.class);
        // parameter type is org.hl7.fhir.instance.model.api.IIdType
        headers.put("CamelFhir.id", patient.getIdElement());

        Patient result = requestBodyAndHeaders("direct://RESOURCE_BY_ID", null, headers);

        assertValidResponse(result);
    }

}