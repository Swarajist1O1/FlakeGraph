class DummyClass_33848 {
    @Test
    public void testOfType() throws Exception {
        org.hl7.fhir.instance.model.api.IBaseConformance result = requestBody("direct://OF_TYPE", CapabilityStatement.class);

        LOG.debug("ofType: " + result);
        assertNotNull(result, "ofType result");
        assertEquals(Enumerations.PublicationStatus.ACTIVE, ((CapabilityStatement) result).getStatus());
    }

}