class DummyClass_156065 {
    @Test(expected=RedditOAuthException.class)
    public void testTokenOAuthProblemException() throws OAuthSystemException, OAuthProblemException, RedditOAuthException {
        when(mockOAuthClient.accessToken(any(OAuthClientRequest.class))).thenThrow(OAuthProblemException.error("Error"));
        subject.token(code);
    }

}