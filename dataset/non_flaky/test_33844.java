class DummyClass_33844 {
    @Test
    public void testWithBundle() throws Exception {
        // using org.hl7.fhir.instance.model.api.IBaseBundle message body for single parameter "bundle"
        Bundle result = requestBody("direct://WITH_BUNDLE", createTransactionBundle());

        assertNotNull(result, "withBundle result");
        assertTrue(result.getEntry().get(0).getResponse().getStatus().contains("Created"));
        LOG.debug("withBundle: " + result);
    }

}