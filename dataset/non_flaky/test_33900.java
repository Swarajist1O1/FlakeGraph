class DummyClass_33900 {
    @Test
    public void testResourceByStringUrlAndStringResource() throws Exception {
        final Map<String, Object> headers = new HashMap<>();
        // parameter type is String
        headers.put("CamelFhir.resourceClass", "Patient");
        // parameter type is org.hl7.fhir.instance.model.api.IIdType
        headers.put("CamelFhir.iUrl", new IdType(this.patient.getId()));

        Patient result = requestBodyAndHeaders("direct://RESOURCE_BY_STRING_URL_AND_STRING_RESOURCE", null, headers);

        assertValidResponse(result);
    }

}