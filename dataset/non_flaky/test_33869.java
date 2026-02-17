class DummyClass_33869 {
    @Test
    public void testOnTypeWithSubsetElements() throws Exception {
        final Map<String, Object> headers = new HashMap<>();
        // parameter type is Class
        headers.put("CamelFhir.resourceType", Patient.class);
        // parameter type is Class
        headers.put("CamelFhir.returnType", Bundle.class);
        // parameter type is Integer
        headers.put("CamelFhir.count", 1);
        // only include the identifier and name
        headers.put(ExtraParameters.SUBSET_ELEMENTS.getHeaderName(), new String[] { "identifier", "name" });

        Bundle result = requestBodyAndHeaders("direct://ON_TYPE", null, headers);

        LOG.debug("onType: " + result);
        assertNotNull(result, "onType result");
        assertEquals(1, result.getEntry().size());
    }

}