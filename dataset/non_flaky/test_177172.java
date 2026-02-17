class DummyClass_177172 {
    @Test
    public void shouldConsumeLogoutRequest_HttpPost() throws Exception {
        final LogoutRequest logoutRequest =
                getLogoutRequest("http://" + spHostname + ':' + rule.httpPort() + "/saml/slo/post",
                                 "http://idp.example.com/post");

        final AggregatedHttpResponse res = sendViaHttpPostBindingProtocol("/saml/slo/post",
                                                                          SAML_REQUEST, logoutRequest);

        assertThat(res.status()).isEqualTo(HttpStatus.OK);
        assertThat(res.contentType()).isEqualTo(MediaType.HTML_UTF_8);

        final Document doc = Jsoup.parse(res.contentUtf8());
        assertThat(doc.body().attr("onLoad")).isEqualTo("document.forms[0].submit()");

        // SAMLResponse will be posted to the IdP's logout response URL.
        final Element form = doc.body().child(0);
        assertThat(form.attr("method")).isEqualTo("post");
        assertThat(form.attr("action")).isEqualTo("http://idp.example.com/saml/slo/post");
        assertThat(form.child(0).attr("name")).isEqualTo(SAML_RESPONSE);
    }

}