class DummyClass_53222 {
    @Test
    public void testContentTypes() {
        String loginUrl = baseUrl + "/login";
        HttpHeaders jsonHeaders = new HttpHeaders();
        jsonHeaders.add("Accept", "application/json");
        ResponseEntity<Map> jsonResponseEntity = restOperations.exchange(loginUrl,
            HttpMethod.GET,
            new HttpEntity<>(jsonHeaders),
            Map.class);
        assertThat(jsonResponseEntity.getHeaders().get("Content-Type").get(0), containsString(APPLICATION_JSON_VALUE));

        HttpHeaders htmlHeaders = new HttpHeaders();
        htmlHeaders.add("Accept", "text/html");
        ResponseEntity<Void> htmlResponseEntity = restOperations.exchange(loginUrl,
            HttpMethod.GET,
            new HttpEntity<>(htmlHeaders),
            Void.class);
        assertThat(htmlResponseEntity.getHeaders().get("Content-Type").get(0), containsString(TEXT_HTML_VALUE));

        HttpHeaders defaultHeaders = new HttpHeaders();
        defaultHeaders.add("Accept", "*/*");
        ResponseEntity<Void> defaultResponseEntity = restOperations.exchange(loginUrl,
            HttpMethod.GET,
            new HttpEntity<>(defaultHeaders),
            Void.class);
        assertThat(defaultResponseEntity.getHeaders().get("Content-Type").get(0), containsString(TEXT_HTML_VALUE));
    }

}