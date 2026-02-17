class DummyClass_35742 {
  @Test
  public void testRouterAuthBypass() throws Exception {
    // mock token validator passes for any token other than "Bearer failme"
    testGet(200, "hello", "/v1/echo/hello", ImmutableMap.of("Authorization", "Bearer x"));
    // so this should fail
    testGet(401, null, "/v1/echo/hello");
    testGet(401, null, "/v1/echo/hello", ImmutableMap.of("Authorization", "Bearer failme"));
    // but /v1/echo/dontfail is configured to bypass auth
    testGet(200, "dontfail", "/v1/echo/dontfail", ImmutableMap.of("Authorization", "Bearer failme"));
    // it only bypasses on exact match, not prefix match
    testGet(401, null, "/v1/echo/dontfailme", ImmutableMap.of("Authorization", "Bearer failme"));

    // /v1/repeat is configured to bypass auth validation, on prefix match
    testGet(200, "hello", "/v1/repeat/hello");
    testGet(200, "hello", "/v1/repeat/hello", ImmutableMap.of("Authorization", "Bearer x"));
    testGet(200, "hello", "/v1/repeat/hello", ImmutableMap.of("Authorization", "Bearer failme"));
    // even with a token that fails validation, we get the correct status code 404
    testGet(404, null, "/v1/repeat/dontfail/me", ImmutableMap.of("Authorization", "Bearer failme"));
  }

}