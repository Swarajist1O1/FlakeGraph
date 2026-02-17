class DummyClass_13860 {
    @Test
    public void applicationProtocolVersionsMustMatch() throws Throwable
    {
        MadeUpServer server = builder.applicationProtocolVersion( (byte) (APPLICATION_PROTOCOL_VERSION + 1) ).server();
        MadeUpClient client = builder.client();
        addToLifeAndStart( server, client );

        try
        {
            client.multiply( 10, 20 );
            fail( "Shouldn't be able to communicate with different application protocol versions" );
        }
        catch ( IllegalProtocolVersionException e )
        { /* Good */ }
    }

}