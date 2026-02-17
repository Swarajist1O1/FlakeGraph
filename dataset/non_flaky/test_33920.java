class DummyClass_33920 {
    @Test
    public void addDuplicate() throws Exception {
        setupData("/org/apache/camel/component/ldif/AddDuplicateSetup.ldif");

        camel.addRoutes(createRouteBuilder(ENDPOINT_LDIF));
        camel.start();

        Endpoint endpoint = camel.getEndpoint(ENDPOINT_START);
        Exchange exchange = endpoint.createExchange();

        // then we set the LDAP filter on the in body
        URL loc = this.getClass().getResource("/org/apache/camel/component/ldif/AddDuplicate.ldif");
        exchange.getIn().setBody(loc.toString());

        // now we send the exchange to the endpoint, and receives the response
        // from Camel
        Exchange out = template.send(endpoint, exchange);

        // Check the results
        List<String> ldifResults = defaultLdapModuleOutAssertions(out);
        assertThat(ldifResults, notNullValue());
        assertThat(ldifResults.size(), equalTo(1));
        assertThat(ldifResults.get(0), not(equalTo("success")));
    }

}