class DummyClass_13853 {
    @Test
    public void clientGetResponseFromServerViaComLayer() throws Throwable
    {
        MadeUpServerImplementation serverImplementation = new MadeUpServerImplementation( storeIdToUse );
        MadeUpServer server = builder.server( serverImplementation );
        MadeUpClient client = builder.client();
        addToLifeAndStart( server, client );

        int value1 = 10;
        int value2 = 5;
        Response<Integer> response = client.multiply( 10, 5 );
        waitUntilResponseHasBeenWritten( server, 1000 );
        assertEquals( (Integer) (value1 * value2), response.response() );
        assertTrue( serverImplementation.gotCalled() );
        assertTrue( server.responseHasBeenWritten() );
    }

}