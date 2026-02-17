class DummyClass_20940 {
    @Test
    public void testX509AuthenticationLogin() {
        PreAuthenticatedAuthenticationToken token = new PreAuthenticatedAuthenticationToken("example.com",
                "doesn't matter what I put here");
        Authentication auth = AuthenticationService.getAuthenticationManager().authenticate(token);
        Collection<? extends GrantedAuthority> authorizations = auth.getAuthorities();
        authorizations.forEach(a -> {
            Assert.assertTrue(
                    a.getAuthority().equals("D") || a.getAuthority().equals("E") || a.getAuthority().equals("F"));
        });
    }

}