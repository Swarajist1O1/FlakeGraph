class DummyClass_33918 {
    @Test
    public void addOne() throws Exception {
        camel.addRoutes(createRouteBuilder(ENDPOINT_LDIF));
        camel.start();

        Endpoint endpoint = camel.getEndpoint(ENDPOINT_START);
        Exchange exchange = endpoint.createExchange();

        // then we set the LDAP filter on the in body
        URL loc = this.getClass().getResource("/org/apache/camel/component/ldif/AddOne.ldif");
        exchange.getIn().setBody(loc.toString());

        // now we send the exchange to the endpoint, and receives the response
        // from Camel
        Exchange out = template.send(endpoint, exchange);

        // Check the results
        List<String> ldifResults = defaultLdapModuleOutAssertions(out);
        assertThat(ldifResults, notNullValue());
        assertThat(ldifResults.size(), equalTo(2)); // Container and user
        assertThat(ldifResults.get(0), equalTo("success"));
        assertThat(ldifResults.get(1), equalTo("success"));

        // Check LDAP
        SearchResult sr;
        NamingEnumeration<SearchResult> searchResults = ldapContext.search("dc=example,dc=org", "(uid=test*)", SEARCH_CONTROLS);
        assertNotNull(searchResults);

        checkDN("uid=test1", searchResults);
    }

}