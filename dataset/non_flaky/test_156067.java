class DummyClass_156067 {
    @Test
    public void testRefreshTokenSuccess() throws RedditOAuthException, OAuthSystemException, OAuthProblemException {
        assertTrue(subject.refreshToken(mockRedditTokenRefreshable));
        verify(mockOAuthClient).accessToken(any(OAuthClientRequest.class));
        verify(mockRedditTokenRefreshable).refresh(null);
    }

}