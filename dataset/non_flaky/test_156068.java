class DummyClass_156068 {
    @Test(expected=RedditOAuthException.class)
    public void testRefreshTokenOAuthSystemException() throws OAuthSystemException, OAuthProblemException, RedditOAuthException {
        when(mockOAuthClient.accessToken(any(OAuthClientRequest.class))).thenThrow(new OAuthSystemException());
        subject.refreshToken(mockRedditTokenRefreshable);
    }

}