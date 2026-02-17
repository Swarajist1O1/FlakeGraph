class DummyClass_53206 {
    @Test
    public void testDefaultScopes() {
        params.set(
                        "credentials",
                        String.format("{\"username\":\"%s\",\"password\":\"%s\"}", testAccounts.getUserName(),
                                        testAccounts.getPassword()));
        ResponseEntity<Void> response = serverRunning.postForResponse(serverRunning.getAuthorizationUri(), headers,
                        params);
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        String location = response.getHeaders().getLocation().toString();
        assertTrue("Not authenticated (no access token): " + location, location.contains("access_token"));
    }

}