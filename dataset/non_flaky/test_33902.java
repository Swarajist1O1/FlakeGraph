class DummyClass_33902 {
    @Test
    public void testResourceByUrlAndStringResourcePrettyPrint() throws Exception {
        final Map<String, Object> headers = new HashMap<>();
        // parameter type is String
        headers.put("CamelFhir.resourceClass", "Patient");
        // parameter type is String
        headers.put("CamelFhir.url", this.patient.getId());
        headers.put(ExtraParameters.PRETTY_PRINT.getHeaderName(), Boolean.TRUE);

        Patient result = requestBodyAndHeaders("direct://RESOURCE_BY_URL_AND_STRING_RESOURCE", null, headers);

        assertValidResponse(result);
    }

}