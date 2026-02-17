class DummyClass_13863 {
    @Test
    public void internalProtocolVersionsMustMatchMultiJvm() throws Throwable
    {
        ServerInterface server = builder.internalProtocolVersion( (byte) 1 ).serverInOtherJvm();
        server.awaitStarted();
        MadeUpClient client = builder.port( MadeUpServerProcess.PORT ).internalProtocolVersion( (byte) 2 ).client();
        life.add( client );
        life.start();

        try
        {
            client.multiply( 10, 20 );
            fail( "Shouldn't be able to communicate with different application protocol versions" );
        }
        catch ( IllegalProtocolVersionException e )
        { /* Good */ }

        server.shutdown();
    }

}