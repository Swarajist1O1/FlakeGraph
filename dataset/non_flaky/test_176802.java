class DummyClass_176802 {
    @Test
    public void testBody() {
        String body = "{\n" +
            " \"business\": {\n" +
            " \"businessDirection\": 1006415,\n" +
            " \"transactionType\": 101759,\n" +
            " \"businessSegment\": 1022645\n" +
            " },\n" +
            " \"contractName\": \"@contractName\",\n" +
            " \"underwritingYear\": 2015,\n" +
            " \"businessAndParticipationType\": 1001011,\n" +
            " \"agreementType\": \"@agreementType\",\n" +
            " \"fasClassification\": \"@fasClassification\",\n" +
            " \"accountingBasis\": 100003,\n" +
            " \"underwritingObjectStatus\": 1003797,\n" +
            " \"inceptionDate\": \"2015-01-01T00:00:00.000+0000\",\n" +
            " \"expirationDate\": \"2015-12-31T00:00:00.000+0000\",\n" +
            " \"contractCurrency\": \"EUR\",\n" +
            " \"profitCentre\": @profitCentre,\n" +
            " \"involvedParties\": [\n" +
            " {\n" +
            " \"partnerId\": \"@partnerId_1\",\n" +
            " \"partnerRole\": @partnerRole\n" +
            " },\n" +
            " {\n" +
            " \"partnerId\": @partnerId_2,\n" +
            " \"partnerRole\": 2173\n" +
            " }\n" +
            " ]\n" +
            "}";

        String processBody = TemplatingEngine.processBody(body);

        assertTrue(processBody.contains("\"contractName\": \"test1\""));
        assertTrue(processBody.contains("\"profitCentre\": 24342"));
    }

}