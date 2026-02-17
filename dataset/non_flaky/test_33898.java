class DummyClass_33898 {
    @Test
    public void testResourceByiUrl() throws Exception {
        final Map<String, Object> headers = new HashMap<>();
        // parameter type is Class
        headers.put("CamelFhir.resource", Patient.class);
        // parameter type is org.hl7.fhir.instance.model.api.IIdType
        headers.put("CamelFhir.iUrl", new IdType(this.patient.getId()));

        Patient result = requestBodyAndHeaders("direct://RESOURCE_BY_IURL", null, headers);

        assertValidResponse(result);
    }

}