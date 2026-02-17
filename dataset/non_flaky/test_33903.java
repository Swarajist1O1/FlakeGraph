class DummyClass_33903 {
    @Test
    public void testUnmarshalWithExplicitUTF16Charset() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.setExpectedMessageCount(1);

        // Message with explicit encoding in MSH
        String charset = "ASCII";
        byte[] body = HL7_MESSAGE.getBytes(Charset.forName(charset));
        template.sendBodyAndHeader("direct:input", new ByteArrayInputStream(body), Exchange.CHARSET_NAME, charset);

        mock.assertIsSatisfied();

        MethodOutcome result = mock.getExchanges().get(0).getIn().getBody(MethodOutcome.class);
        assertNotNull(result, "resource result");
        assertTrue(result.getCreated());
    }

}