class DummyClass_20938 {
    @Test(expected = BadCredentialsException.class)
    public void testBasicAuthenticationFailure() {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("test", "test2");
        AuthenticationService.getAuthenticationManager().authenticate(token);
    }

}