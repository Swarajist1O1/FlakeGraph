class DummyClass_53154 {
    @Test
    public void test_default_values() throws Exception {
        TokenPolicy policy = new TokenPolicy();
        assertFalse(policy.isRefreshTokenUnique());
        assertFalse(policy.isJwtRevocable());
        assertEquals(TokenConstants.TokenFormat.JWT.getStringValue(), policy.getRefreshTokenFormat());
    }

}