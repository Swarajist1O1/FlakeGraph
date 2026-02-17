class DummyClass_156069 {
    @Test(expected=RedditOAuthException.class)
    public void testRefreshTokenOAuthProblemException() throws OAuthSystemException, OAuthProblemException, RedditOAuthException {
        when(mockOAuthClient.accessToken(any(OAuthClientRequest.class))).thenThrow(OAuthProblemException.error("Error"));
        subject.refreshToken(mockRedditTokenRefreshable);
    }

}