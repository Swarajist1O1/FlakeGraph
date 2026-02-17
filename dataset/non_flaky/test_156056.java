class DummyClass_156056 {
    @Test
    public void testGetters() {
        
        RedditToken subject = new RedditToken(jsonToken);
        RedditToken subjectUserProvided = new RedditToken(accessToken, tokenType, expiresIn, scope);
        assertEquals(accessToken, subject.getAccessToken());
        assertEquals(refreshToken, subject.getRefreshToken());
        assertEquals(tokenType, subject.getTokenType());
        assertEquals(expiresIn, subject.getExpirationSpan());
        assertTrue(subject.hasScope(RedditScope.EDIT));
        assertTrue(subject.hasScope(RedditScope.FLAIR));
        assertFalse(subject.hasScope(RedditScope.PRIVATEMESSAGE));
        assertTrue(subject.isRefreshable());
        assertFalse(subjectUserProvided.isRefreshable());
        
    }

}