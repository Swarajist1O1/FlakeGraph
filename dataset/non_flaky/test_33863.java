class DummyClass_33863 {
    @Test
    public void testCreateResource() throws Exception {
        Patient patient = new Patient().addName(new HumanName().addGiven("Vincent").setFamily("Freeman"));

        MethodOutcome result = requestBody("direct://RESOURCE", patient);

        LOG.debug("resource: " + result);
        assertNotNull(result, "resource result");
        assertTrue(result.getCreated());
    }

}