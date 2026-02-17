class DummyClass_177171 {
    @Test
    public void shouldConsumeAssertion_HttpRedirect() throws Exception {
        final Response response =
                getAuthResponse("http://" + spHostname + ':' + rule.httpPort() + "/saml/acs/redirect");
        final AggregatedHttpResponse res = sendViaHttpRedirectBindingProtocol("/saml/acs/redirect",
                                                                              SAML_RESPONSE, response);

        assertThat(res.status()).isEqualTo(HttpStatus.FOUND);
        assertThat(res.headers().get(HttpHeaderNames.LOCATION)).isEqualTo("/");
    }

}