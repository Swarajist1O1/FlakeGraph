class DummyClass_33851 {
    @Test
    public void testDeleteResourceById() throws Exception {
        assertTrue(patientExists());

        // using org.hl7.fhir.instance.model.api.IIdType message body for single parameter "id"
        IBaseOperationOutcome result = requestBody("direct://RESOURCE_BY_ID", this.patient.getIdElement());

        LOG.debug("resourceById: " + result);
        assertNotNull(result, "resourceById result");
        assertFalse(patientExists());
    }

}