class DummyClass_133956 {
    @Test
    public void testTokenResponseDefaultMap() {
        Token token = new Token(0L, 0L);
        Map<UUID, Long> backPointerMap = getTokenResponseDefaultMap();
        Map<UUID, Long> streamTails = getTokenResponseDefaultMap();
        ResponseMsg response = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.CHECK),
                getTokenResponseMsg(
                        TokenType.NORMAL,
                        TokenResponse.NO_CONFLICT_KEY,
                        TokenResponse.NO_CONFLICT_STREAM, token,
                        backPointerMap,
                        streamTails)
        );

        sequencerHandler.handleMessage(response, mockChannelHandlerContext);
        ArgumentCaptor<TokenResponse> captor = ArgumentCaptor.forClass(TokenResponse.class);
        // Verify that the correct request was completed (once) with the appropriate value,
        // and that we did not complete exceptionally.
        verify(mockClientRouter, never()).completeExceptionally(anyLong(), any(Throwable.class));
        verify(mockClientRouter).completeRequest(eq(response.getHeader().getRequestId()), captor.capture());

        TokenResponse tokenResponse = captor.getValue();
        assertEquals(token, tokenResponse.getToken());
        assertEquals(TokenType.NORMAL, tokenResponse.getRespType());
        assertEquals(TokenResponse.NO_CONFLICT_STREAM, tokenResponse.getConflictStream());
        assertEquals(streamTails.size(), tokenResponse.getStreamTailsCount());
        assertEquals(backPointerMap, tokenResponse.getBackpointerMap());
        assertArrayEquals(tokenResponse.getConflictKey(), TokenResponse.NO_CONFLICT_KEY);
    }

}