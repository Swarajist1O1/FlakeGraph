class DummyClass_156064 {
    @Test(expected=RedditOAuthException.class)
    public void testTokenOAuthSystemException() throws OAuthSystemException, OAuthProblemException, RedditOAuthException {
        when(mockOAuthClient.accessToken(any(OAuthClientRequest.class))).thenThrow(new OAuthSystemException());
        subject.token(code);
    }

}