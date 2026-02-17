class DummyClass_156072 {
    @Test(expected=RedditOAuthException.class)
    public void testTokenAppOnlyOAuthSystemException() throws OAuthSystemException, OAuthProblemException, RedditOAuthException {
        when(mockOAuthClient.accessToken(any(OAuthClientRequest.class))).thenThrow(new OAuthSystemException());
        subject.tokenAppOnly(false);
    }

}