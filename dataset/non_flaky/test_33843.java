class DummyClass_33843 {
    @Test
    public void testProcessMessage() throws Exception {
        final Map<String, Object> headers = new HashMap<>();
        // parameter type is String
        headers.put("CamelFhir.respondToUri", null);
        // parameter type is org.hl7.fhir.instance.model.api.IBaseBundle
        headers.put("CamelFhir.msgBundle", null);
        headers.put("CamelFhir.asynchronous", Boolean.FALSE);
        // parameter type is Class
        headers.put("CamelFhir.responseClass", null);
        // parameter type is java.util.Map
        headers.put("CamelFhir.extraParameters", null);

        final org.hl7.fhir.instance.model.api.IBaseBundle result
                = requestBodyAndHeaders("direct://PROCESS_MESSAGE", null, headers);

        assertNotNull(result, "processMessage result");
        LOG.debug("processMessage: " + result);
    }

}