class DummyClass_177166 {
    @Test
    public void shouldRespondAuthnRequest_HttpRedirect() throws Exception {
        final AggregatedHttpResponse resp = client.get("/redirect").aggregate().join();
        assertThat(resp.status()).isEqualTo(HttpStatus.FOUND);

        // Check the order of the parameters in the quest string.
        final String location = resp.headers().get(HttpHeaderNames.LOCATION);
        final Pattern p = Pattern.compile(
                "http://idp\\.example\\.com/saml/sso/redirect\\?" +
                "SAMLRequest=([^&]+)&RelayState=([^&]+)&SigAlg=([^&]+)&Signature=(.+)$");
        assertThat(location).isNotNull();
        assertThat(p.matcher(location).matches()).isTrue();

        assertThat(QueryParams.fromQueryString(location)
                              .get(SIGNATURE_ALGORITHM)).isEqualTo(signatureAlgorithm);
    }

}