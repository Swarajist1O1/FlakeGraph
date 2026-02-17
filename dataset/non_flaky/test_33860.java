class DummyClass_33860 {
    @Test
    public void testPatchByUrl() throws Exception {
        final Map<String, Object> headers = new HashMap<>();
        // parameter type is String
        headers.put("CamelFhir.patchBody", PATCH);
        // parameter type is String
        headers.put("CamelFhir.url", "Patient?given=Vincent&family=Freeman");
        // parameter type is ca.uhn.fhir.rest.api.PreferReturnEnum
        headers.put("CamelFhir.preferReturn", null);

        MethodOutcome result = requestBodyAndHeaders("direct://PATCH_BY_URL", null, headers);

        assertNotNull(result, "patchByUrl result");
        LOG.debug("patchByUrl: " + result);
        assertActive(result);
    }

}