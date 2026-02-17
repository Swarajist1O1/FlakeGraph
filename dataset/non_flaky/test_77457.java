class DummyClass_77457 {
@TestRuleLimitSysouts.Limit(bytes = 14000)
    public void testRestClient() throws URISyntaxException, IOException {
        final String baseUrl = buildBaseUrl();

        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            final String endpoint = baseUrl + "/employees/1";
            logger.info("Connecting to uri: " + baseUrl);

            final HttpPut put = new HttpPut(new URI(endpoint));

            final String body = "{"
                + "  \"first_name\": \"John\","
                + "  \"last_name\": \"Smith\","
                + "  \"age\": 25,"
                + "  \"about\": \"I love to go rock climbing\","
                + "  \"interests\": ["
                + "    \"sports\","
                + "    \"music\""
                + "  ]"
                + "}";

            put.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
            try (CloseableHttpResponse response = client.execute(put)) {
                int status = response.getStatusLine().getStatusCode();
                assertThat(
                    "expected a 201 response but got: " + status + " - body: " + EntityUtils.toString(response.getEntity()),
                    status,
                    equalTo(201)
                );
            }

            logger.info("Fetching resource at " + endpoint);

            final HttpGet get = new HttpGet(new URI(endpoint));
            try (
                CloseableHttpResponse response = client.execute(get);
                XContentParser parser = JsonXContent.jsonXContent.createParser(
                    new NamedXContentRegistry(ClusterModule.getNamedXWriteables()),
                    DeprecationHandler.THROW_UNSUPPORTED_OPERATION,
                    response.getEntity().getContent()
                )
            ) {
                final Map<String, Object> map = parser.map();
                assertThat(map.get("first_name"), equalTo("John"));
                assertThat(map.get("last_name"), equalTo("Smith"));
                assertThat(map.get("age"), equalTo(25));
                assertThat(map.get("about"), equalTo("I love to go rock climbing"));
                final Object interests = map.get("interests");
                assertThat(interests, instanceOf(List.class));
                @SuppressWarnings("unchecked")
                final List<String> interestsAsList = (List<String>) interests;
                assertThat(interestsAsList, containsInAnyOrder("sports", "music"));
            }
        }
    }

}