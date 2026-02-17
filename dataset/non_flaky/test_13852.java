class DummyClass_13852 {
    @Test
    public void shouldSendExceptionBackToClientOnInvalidChecksum() throws Exception
    {
        // Given
        Server<Object, Object> server = newServer( checksumVerifier );
        RequestContext ctx = new RequestContext( 0, 1, 0, 1, 12 );

        doThrow(new IllegalStateException("123")).when(checksumVerifier).assertMatch( anyLong(), anyLong() );

        // When
        try
        {
            server.messageReceived( channelCtx( channel ), message( reqType, ctx, channel, EMPTY_SERIALIZER ) );
            fail("Should have failed.");
        }
        catch(IllegalStateException e)
        {
            // Expected
        }

        // Then
        try
        {
            protocol.deserializeResponse( channel.asBlockingReadHandler(), ByteBuffer.allocateDirect( 1024 ), 1,
                    VOID_DESERIALIZER, mock( ResourceReleaser.class ) );
            fail("Should have failed.");
        }
        catch(IllegalStateException e)
        {
            assertThat(e.getMessage(), equalTo("123"));
        }

    }

}