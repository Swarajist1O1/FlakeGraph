class DummyClass_33919 {
    @Test
    public void deleteOne() throws Exception {
        setupData("/org/apache/camel/component/ldif/DeleteOneSetup.ldif");

        camel.addRoutes(createRouteBuilder(ENDPOINT_LDIF));
        camel.start();

        Endpoint endpoint = camel.getEndpoint(ENDPOINT_START);
        Exchange exchange = endpoint.createExchange();

        // then we set the LDAP filter on the in body
        URL loc = this.getClass().getResource("/org/apache/camel/component/ldif/DeleteOne.ldif");
        exchange.getIn().setBody(loc.toString());

        // now we send the exchange to the endpoint, and receives the response
        // from Camel
        Exchange out = template.send(endpoint, exchange);

        // Check the results
        List<String> ldifResults = defaultLdapModuleOutAssertions(out);
        assertThat(ldifResults, notNullValue());
        assertThat(ldifResults.size(), equalTo(1));
        assertThat(ldifResults.get(0), equalTo("success"));

        // Check LDAP
        NamingEnumeration<SearchResult> searchResults = ldapContext.search("dc=example,dc=org", "(uid=test*)", SEARCH_CONTROLS);
        // test2
        while (searchResults.hasMore()) {
            assertThat(searchResults.next().getName(), not(containsString("test2")));
        }
    }

}