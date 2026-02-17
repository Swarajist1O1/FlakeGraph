class DummyClass_13864 {
    @Test
    public void serverStopsStreamingToDeadClient() throws Throwable
    {
        MadeUpServer server = builder.server();
        MadeUpClient client = builder.client();
        addToLifeAndStart( server, client );

        int failAtSize = FRAME_LENGTH / 2;
        ClientCrashingWriter writer = new ClientCrashingWriter( client, failAtSize );
        try
        {
            client.fetchDataStream( writer, FRAME_LENGTH * 10 );
            assertTrue( writer.getSizeRead() >= failAtSize );
            fail( "Should fail in the middle" );
        }
        catch ( ComException e )
        {   // Expected
        }
        assertTrue( writer.getSizeRead() >= failAtSize );

        long maxWaitUntil = System.currentTimeMillis() + 2 * 1000;
        while ( !server.responseFailureEncountered() && System.currentTimeMillis() < maxWaitUntil )
        {
            yield();
        }
        assertTrue( "Failure writing the response should have been encountered", server.responseFailureEncountered() );
        assertFalse( "Response shouldn't have been successful", server.responseHasBeenWritten() );
    }

}