class DummyClass_156073 {
    @Test(expected=RedditOAuthException.class)
    public void testTokenAppOnlyOAuthProblemException() throws OAuthSystemException, OAuthProblemException, RedditOAuthException {
        when(mockOAuthClient.accessToken(any(OAuthClientRequest.class))).thenThrow(OAuthProblemException.error("Error"));
        subject.tokenAppOnly(false);
    }

}