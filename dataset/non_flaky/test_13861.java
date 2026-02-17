class DummyClass_13861 {
    @Test
    public void applicationProtocolVersionsMustMatchMultiJvm() throws Throwable
    {
        ServerInterface server = builder.applicationProtocolVersion( (byte) (APPLICATION_PROTOCOL_VERSION + 1) )
                                        .serverInOtherJvm();
        server.awaitStarted();
        MadeUpClient client = builder.port( MadeUpServerProcess.PORT ).client();
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