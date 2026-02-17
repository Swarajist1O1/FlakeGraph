class DummyClass_33855 {
    @Test
    public void testResource() throws Exception {
        Patient bobbyHebb = new Patient().addName(new HumanName().addGiven("Bobby").setFamily("Hebb"));
        // using org.hl7.fhir.instance.model.api.IBaseResource message body for single parameter "resource"
        MethodOutcome result = requestBody("direct://RESOURCE", bobbyHebb);

        assertNotNull(result, "resource result");
        LOG.debug("resource: " + result);
        assertNotNull(result.getOperationOutcome());
        assertTrue(((OperationOutcome) result.getOperationOutcome()).getText().getDivAsString()
                .contains("No issues detected during validation"));
    }

}