class DummyClass_33850 {
    @Test
    public void testDeleteResource() throws Exception {
        assertTrue(patientExists());
        // using org.hl7.fhir.instance.model.api.IBaseResource message body for single parameter "resource"
        IBaseOperationOutcome result = requestBody("direct://RESOURCE", this.patient);

        LOG.debug("resource: " + result);
        assertNotNull(result, "resource result");
        assertFalse(patientExists());
    }

}