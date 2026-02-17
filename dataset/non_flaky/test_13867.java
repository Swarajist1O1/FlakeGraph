class DummyClass_13867 {
    @Test
    public void serverCanReadChunkSizeBiggerThanItsOwn() throws Throwable
    {   // Given that frameLength is the same for both client and server.
        int serverChunkSize = 1000;
        int clientChunkSize = serverChunkSize * 10;
        MadeUpServer server = builder.chunkSize( serverChunkSize ).server();
        MadeUpClient client = builder.chunkSize( clientChunkSize ).client();

        addToLifeAndStart( server, client );

        // Tell server to stream data occupying roughly two chunks. The chunks
        // from server are 10 times bigger than the clients chunk size.
        client.sendDataStream( new DataProducer( clientChunkSize * 2 ) );
    }

}