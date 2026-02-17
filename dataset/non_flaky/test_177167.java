class DummyClass_177167 {
    @Test
    public void shouldRespondAuthnRequest_HttpPost() throws Exception {
        final AggregatedHttpResponse resp = client.get("/post").aggregate().join();
        assertThat(resp.status()).isEqualTo(HttpStatus.OK);
        assertThat(resp.contentType()).isEqualTo(MediaType.HTML_UTF_8);

        final Document doc = Jsoup.parse(resp.contentUtf8());
        assertThat(doc.body().attr("onLoad")).isEqualTo("document.forms[0].submit()");

        // SAMLRequest will be posted to the IdP's SSO URL.
        final Element form = doc.body().child(0);
        assertThat(form.attr("method")).isEqualTo("post");
        assertThat(form.attr("action")).isEqualTo("http://idp.example.com/saml/sso/post");
        assertThat(form.child(0).attr("name")).isEqualTo(SAML_REQUEST);
        assertThat(form.child(1).attr("name")).isEqualTo(RELAY_STATE);
    }

}