class DummyClass_53207 {
    @Test
    public void testInvalidScopes() {
        params.set(
                        "credentials",
                        String.format("{\"username\":\"%s\",\"password\":\"%s\"}", testAccounts.getUserName(),
                                        testAccounts.getPassword()));
        params.set("scope", "read");
        ResponseEntity<Void> response = serverRunning.postForResponse(serverRunning.getAuthorizationUri(), headers,
                        params);
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        String location = response.getHeaders().getLocation().toString();
        // System.err.println(location);
        assertTrue(location.startsWith(params.getFirst("redirect_uri")));
        assertTrue(location.contains("error=invalid_scope"));
        assertFalse(location.contains("credentials="));
    }

}