class DummyClass_13859 {
    @Test
    public void throwingServerSideExceptionBackToClient() throws Throwable
    {
        MadeUpServer server = builder.server();
        MadeUpClient client = builder.client();
        addToLifeAndStart( server, client );

        String exceptionMessage = "The message";
        try
        {
            client.throwException( exceptionMessage );
            fail( "Should have thrown " + MadeUpException.class.getSimpleName() );
        }
        catch ( MadeUpException e )
        {   // Good
            assertEquals( exceptionMessage, e.getMessage() );
        }
    }

}