class DummyClass_177170 {
    @Test
    public void shouldConsumeAssertion_HttpPost() throws Exception {
        final Response response =
                getAuthResponse("http://" + spHostname + ':' + rule.httpPort() + "/saml/acs/post");
        final AggregatedHttpResponse res = sendViaHttpPostBindingProtocol("/saml/acs/post",
                                                                          SAML_RESPONSE, response);

        assertThat(res.status()).isEqualTo(HttpStatus.FOUND);
        assertThat(res.headers().get(HttpHeaderNames.LOCATION)).isEqualTo("/");
    }

}