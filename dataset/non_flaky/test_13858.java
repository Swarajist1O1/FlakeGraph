class DummyClass_13858 {
    @Test
    public void communicateBetweenJvms() throws Throwable
    {
        ServerInterface server = builder.serverInOtherJvm();
        server.awaitStarted();
        MadeUpClient client = builder.port( MadeUpServerProcess.PORT ).client();
        life.add( client );
        life.start();

        assertEquals( (Integer) (9 * 5), client.multiply( 9, 5 ).response() );
        client.fetchDataStream( new ToAssertionWriter(), 1024 * 1024 * 3 );

        server.shutdown();
    }

}