class DummyClass_156062 {
    @Test
    public void testTokenFromInfo() {
        RedditToken token = subject.tokenFromInfo(accessToken, tokenType, expiresIn, scope);
        assertEquals(accessToken, token.getAccessToken());
        assertEquals(tokenType, token.getTokenType());
        assertEquals(expiresIn, token.getExpirationSpan());
        assertTrue(token.hasScope(RedditScope.EDIT));
        assertTrue(token.hasScope(RedditScope.FLAIR));
        assertFalse(token.hasScope(RedditScope.PRIVATEMESSAGE));
    }

}