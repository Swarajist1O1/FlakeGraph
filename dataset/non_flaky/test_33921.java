class DummyClass_33921 {
    @Test
    public void modify() throws Exception {
        setupData("/org/apache/camel/component/ldif/ModifySetup.ldif");

        camel.addRoutes(createRouteBuilder(ENDPOINT_LDIF));
        camel.start();

        Endpoint endpoint = camel.getEndpoint(ENDPOINT_START);
        Exchange exchange = endpoint.createExchange();

        // then we set the LDAP filter on the in body
        URL loc = this.getClass().getResource("/org/apache/camel/component/ldif/Modify.ldif");
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
        SearchResult sr;
        NamingEnumeration<SearchResult> searchResults = ldapContext.search("dc=example,dc=org", "(uid=test*)", SEARCH_CONTROLS);
        assertNotNull(searchResults);

        boolean uidFound = false;
        while (searchResults.hasMore()) {
            sr = searchResults.next();
            if (sr.getName().contains("uid=test4")) {
                uidFound = true;

                // Check the attributes of the search result
                Attributes attribs = sr.getAttributes();
                assertNotNull(attribs);
                Attribute attrib = attribs.get("sn");
                assertNotNull(attribs);
                assertThat(1, equalTo(attrib.size()));
                assertThat("5", equalTo(attrib.get(0).toString()));
            }
        }

        assertThat("uid=test4 not found", uidFound, equalTo(true));
    }

}