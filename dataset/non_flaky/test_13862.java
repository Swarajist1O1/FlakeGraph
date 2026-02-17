class DummyClass_13862 {
    @Test
    public void internalProtocolVersionsMustMatch() throws Throwable
    {
        MadeUpServer server = builder.internalProtocolVersion( (byte) 1 ).server();
        MadeUpClient client = builder.internalProtocolVersion( (byte) 2 ).client();
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