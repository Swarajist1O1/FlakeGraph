class DummyClass_177169 {
    @Test
    public void shouldRespondMetadataWithoutAuthentication() throws Exception {
        final AggregatedHttpResponse resp = client.get("/saml/metadata").aggregate().join();
        assertThat(resp.status()).isEqualTo(HttpStatus.OK);
        assertThat(resp.contentType()).isEqualTo(CONTENT_TYPE_SAML_METADATA);

        final EntityDescriptor metadata =
                (EntityDescriptor) deserialize(resp.contentUtf8().getBytes());
        assertThat(metadata).isNotNull();

        final SPSSODescriptor sp = metadata.getSPSSODescriptor(SAMLConstants.SAML20P_NS);
        assertThat(sp.isAuthnRequestsSigned()).isTrue();
        assertThat(sp.getWantAssertionsSigned()).isTrue();

        final List<KeyDescriptor> kd = sp.getKeyDescriptors();
        assertThat(kd.get(0).getUse().name()).isEqualToIgnoringCase("signing");
        assertThat(kd.get(1).getUse().name()).isEqualToIgnoringCase("encryption");

        final List<SingleLogoutService> slo = sp.getSingleLogoutServices();
        assertThat(slo.get(0).getLocation())
                .isEqualTo("http://" + spHostname + ':' + rule.httpPort() + "/saml/slo/post");
        assertThat(slo.get(0).getBinding()).isEqualTo(SAMLConstants.SAML2_POST_BINDING_URI);
        assertThat(slo.get(1).getLocation())
                .isEqualTo("http://" + spHostname + ':' + rule.httpPort() + "/saml/slo/redirect");
        assertThat(slo.get(1).getBinding()).isEqualTo(SAMLConstants.SAML2_REDIRECT_BINDING_URI);

        final List<AssertionConsumerService> acs = sp.getAssertionConsumerServices();
        // index 0 (default)
        assertThat(acs.get(0).getIndex()).isEqualTo(0);
        assertThat(acs.get(0).isDefault()).isTrue();
        assertThat(acs.get(0).getLocation())
                .isEqualTo("http://" + spHostname + ':' + rule.httpPort() + "/saml/acs/post");
        assertThat(acs.get(0).getBinding()).isEqualTo(SAMLConstants.SAML2_POST_BINDING_URI);
        // index 1
        assertThat(acs.get(1).getIndex()).isEqualTo(1);
        assertThat(acs.get(1).isDefault()).isFalse();
        assertThat(acs.get(1).getLocation())
                .isEqualTo("http://" + spHostname + ':' + rule.httpPort() + "/saml/acs/redirect");
        assertThat(acs.get(1).getBinding()).isEqualTo(SAMLConstants.SAML2_REDIRECT_BINDING_URI);
    }

}