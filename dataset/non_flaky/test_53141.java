class DummyClass_53141 {
    @Test
    public void testHappyDay() throws Exception {

        RestOperations restTemplate = serverRunning.createRestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(serverRunning.getUrl("/api/apps"), String.class);
        // first make sure the resource is actually protected.
        assertNotSame(HttpStatus.OK, response.getStatusCode());
        HttpHeaders approvalHeaders = new HttpHeaders();
        OAuth2AccessToken accessToken = context.getAccessToken();
        approvalHeaders.set("Authorization", "bearer " + accessToken.getValue());

        ResponseEntity<String> result = serverRunning.getForString("/api/apps");
        assertEquals(HttpStatus.OK, result.getStatusCode());
        String body = result.getBody();
        assertTrue("Wrong response: " + body, body.contains("dsyerapi.cloudfoundry.com"));

    }

}