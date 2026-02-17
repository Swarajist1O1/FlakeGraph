class DummyClass_13856 {
    @Test
    public void makeSureClientCanStreamBigData() throws Throwable
    {
        MadeUpServer server = builder.server();
        MadeUpClient client = builder.client();
        addToLifeAndStart( server, client );


        client.fetchDataStream( new ToAssertionWriter(), FRAME_LENGTH * 3 );
    }

}