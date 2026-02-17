class DummyClass_20939 {
    @Test
    public void testBasicAuthenticationLogin() {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("test", "test1");
        Authentication auth = AuthenticationService.getAuthenticationManager().authenticate(token);
        Collection<? extends GrantedAuthority> authorizations = auth.getAuthorities();
        authorizations.forEach(a -> {
            Assert.assertTrue(
                    a.getAuthority().equals("A") || a.getAuthority().equals("B") || a.getAuthority().equals("C"));
        });
    }

}