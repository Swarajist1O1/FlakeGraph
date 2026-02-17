class DummyClass_33864 {
    @Test
    public void testCreateStringResource() throws Exception {
        Patient patient = new Patient().addName(new HumanName().addGiven("Vincent").setFamily("Freeman"));
        String patientString = this.fhirContext.newXmlParser().encodeResourceToString(patient);

        MethodOutcome result = requestBody("direct://RESOURCE_STRING", patientString);

        LOG.debug("resource: " + result);
        assertNotNull(result, "resource result");
        assertTrue(result.getCreated());
    }

}