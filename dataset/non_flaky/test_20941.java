class DummyClass_20941 {
    @Test(expected = UsernameNotFoundException.class)
    public void testX509AuthenticationLoginFailed() {
        PreAuthenticatedAuthenticationToken token = new PreAuthenticatedAuthenticationToken("bad.example.com",
                "doesn't matter what I put here");
        Authentication auth = AuthenticationService.getAuthenticationManager().authenticate(token);
        Collection<? extends GrantedAuthority> authorizations = auth.getAuthorities();
        authorizations.forEach(a -> {
            Assert.assertTrue(
                    a.getAuthority().equals("D") || a.getAuthority().equals("E") || a.getAuthority().equals("F"));
        });
    }

}