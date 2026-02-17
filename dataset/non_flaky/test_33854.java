class DummyClass_33854 {
    @Test
    public void testDeleteResourceConditionalByUrlCacheControlDirective() throws Exception {
        assertTrue(patientExists());
        Map<String, Object> headers = new HashMap<>();
        headers.put(ExtraParameters.CACHE_CONTROL_DIRECTIVE.getHeaderName(), new CacheControlDirective().setNoCache(true));

        IBaseOperationOutcome result = requestBodyAndHeaders("direct://RESOURCE_CONDITIONAL_BY_URL",
                "Patient?given=Vincent&family=Freeman", headers);

        LOG.debug("resourceConditionalByUrl: " + result);
        assertNotNull(result, "resourceConditionalByUrl result");
        assertFalse(patientExists());
    }

}