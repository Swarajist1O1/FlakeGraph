class DummyClass_33858 {
    @Test
    public void testPatchByStringId() throws Exception {
        final Map<String, Object> headers = new HashMap<>();
        // parameter type is String
        headers.put("CamelFhir.patchBody", PATCH);
        // parameter type is String
        headers.put("CamelFhir.stringId", this.patient.getId());
        // parameter type is ca.uhn.fhir.rest.api.PreferReturnEnum
        headers.put("CamelFhir.preferReturn", null);

        MethodOutcome result = requestBodyAndHeaders("direct://PATCH_BY_SID", null, headers);
        assertActive(result);
    }

}