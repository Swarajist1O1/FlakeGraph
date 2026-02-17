class DummyClass_33841 {
    @Test
    public void testOnServer() throws Exception {
        final Map<String, Object> headers = new HashMap<>();
        // parameter type is String
        headers.put("CamelFhir.name", "$get-resource-counts");
        // parameter type is org.hl7.fhir.instance.model.api.IBaseParameters
        headers.put("CamelFhir.parameters", null);
        // parameter type is Class
        headers.put("CamelFhir.outputParameterType", Parameters.class);
        headers.put("CamelFhir.useHttpGet", Boolean.TRUE);
        // parameter type is Class
        headers.put("CamelFhir.returnType", null);
        // parameter type is java.util.Map
        headers.put("CamelFhir.extraParameters", null);

        final Parameters result = requestBodyAndHeaders("direct://ON_SERVER", null, headers);
        assertNotNull(result, "onServer result");
    }

}