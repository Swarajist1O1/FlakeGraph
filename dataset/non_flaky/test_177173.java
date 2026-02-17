class DummyClass_177173 {
    @Test
    public void shouldConsumeLogoutRequest_HttpRedirect() throws Exception {
        final LogoutRequest logoutRequest =
                getLogoutRequest("http://" + spHostname + ':' + rule.httpPort() + "/saml/slo/redirect",
                                 "http://idp.example.com/redirect");

        final AggregatedHttpResponse res =
                sendViaHttpRedirectBindingProtocol("/saml/slo/redirect", SAML_REQUEST, logoutRequest);

        assertThat(res.status()).isEqualTo(HttpStatus.FOUND);

        // Check the order of the parameters in the quest string.
        final String location = res.headers().get(HttpHeaderNames.LOCATION);
        final Pattern p = Pattern.compile(
                "http://idp\\.example\\.com/saml/slo/redirect\\?" +
                "SAMLResponse=([^&]+)&SigAlg=([^&]+)&Signature=(.+)$");
        assertThat(location).isNotNull();
        assertThat(p.matcher(location).matches()).isTrue();
    }

}