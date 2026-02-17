class DummyClass_156071 {
    @Test
    public void testTokenAppOnly() throws RedditOAuthException, OAuthSystemException, OAuthProblemException {
        
        // Captor for the request that is executed
        ArgumentCaptor<OAuthClientRequest> clientCaptor = ArgumentCaptor.forClass(OAuthClientRequest.class);
        
        when(mockOAuthClient.accessToken(any(OAuthClientRequest.class))).thenReturn(jsonTokenNonRefreshable);
        
        // Run subject
        RedditToken token = subject.tokenAppOnly(false);
        
        // Verify and capture
        verify(mockOAuthClient).accessToken(clientCaptor.capture());
        
        OAuthClientRequest request = clientCaptor.getValue();
        
        assertNotNull(request.getHeader("Authorization")); // This is Base64 encoded
        assertEquals(request.getHeader("User-Agent"), userAgent);
        
        assertEquals(accessToken, token.getAccessToken());
        assertNull(token.getRefreshToken());
        assertEquals(tokenType, token.getTokenType());
        assertEquals(expiresIn, token.getExpirationSpan());
        assertTrue(token.hasScope(RedditScope.EDIT));
        assertTrue(token.hasScope(RedditScope.FLAIR));
        assertFalse(token.hasScope(RedditScope.PRIVATEMESSAGE));

    }

}