class DummyClass_13868 {
    @Test
    public void impossibleToHaveBiggerChunkSizeThanFrameSize() throws Throwable
    {
        Builder myBuilder = builder.chunkSize( MadeUpServer.FRAME_LENGTH + 10 );
        try
        {
            myBuilder.server().start();
            fail( "Shouldn't be possible" );
        }
        catch ( IllegalArgumentException e )
        {   // Good
        }

        try
        {
            myBuilder.client();
            fail( "Shouldn't be possible" );
        }
        catch ( IllegalArgumentException e )
        {   // Good
        }
    }

}