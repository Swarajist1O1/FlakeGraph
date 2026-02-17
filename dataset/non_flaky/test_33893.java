class DummyClass_33893 {
    @Test
    public void testResourceByIdAndStringResource() throws Exception {
        final Map<String, Object> headers = new HashMap<>();
        // parameter type is Class
        headers.put("CamelFhir.resourceClass", "Patient");
        // parameter type is org.hl7.fhir.instance.model.api.IIdType
        headers.put("CamelFhir.id", patient.getIdElement());

        Patient result = requestBodyAndHeaders("direct://RESOURCE_BY_ID_AND_STRING_RESOURCE", null, headers);

        assertValidResponse(result);
    }

}