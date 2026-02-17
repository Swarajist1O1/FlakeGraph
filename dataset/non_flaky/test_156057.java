class DummyClass_156057 {
    @Test
    public void testRefresh() {
        
        RedditToken subject = new RedditToken(jsonToken);
        assertEquals(accessToken, subject.getAccessToken());
        assertEquals(refreshToken, subject.getRefreshToken());
        assertEquals(tokenType, subject.getTokenType());
        assertEquals(expiresIn, subject.getExpirationSpan());
        assertTrue(subject.hasScope(RedditScope.EDIT));
        assertTrue(subject.hasScope(RedditScope.FLAIR));
        
        subject.refresh(refreshJsonToken);
        
        assertEquals(accessToken2, subject.getAccessToken());
        assertEquals(refreshToken, subject.getRefreshToken());
        assertEquals(tokenType2, subject.getTokenType());
        assertEquals(expiresIn2, subject.getExpirationSpan());
        assertTrue(subject.hasScope(RedditScope.EDIT));
        assertFalse(subject.hasScope(RedditScope.FLAIR));
        
    }

}