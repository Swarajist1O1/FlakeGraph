class DummyClass_33874 {
    @Test
    public void testResourceAsStringWithStringId() throws Exception {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("1998-04-29");
        assertNotEquals(date, patient.getBirthDate());
        this.patient.setBirthDate(date);
        final Map<String, Object> headers = new HashMap<>();
        // parameter type is org.hl7.fhir.instance.model.api.IBaseResource
        headers.put("CamelFhir.resourceAsString", this.fhirContext.newJsonParser().encodeResourceToString(this.patient));
        // parameter type is org.hl7.fhir.instance.model.api.IIdType
        headers.put("CamelFhir.stringId", this.patient.getIdElement().getIdPart());
        // parameter type is ca.uhn.fhir.rest.api.PreferReturnEnum
        headers.put("CamelFhir.preferReturn", PreferReturnEnum.REPRESENTATION);

        MethodOutcome result = requestBodyAndHeaders("direct://RESOURCE_AS_STRING_WITH_STRING_ID", null, headers);

        assertNotNull(result, "resource result");
        LOG.debug("resource: " + result);
        assertEquals(date, ((Patient) result.getResource()).getBirthDate(), "Birth date not updated!");
    }

}