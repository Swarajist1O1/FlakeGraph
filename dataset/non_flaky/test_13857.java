class DummyClass_13857 {
    @Test
    public void clientThrowsServerSideErrorMidwayThroughStreaming() throws Throwable
    {
        final String failureMessage = "Just failing";
        MadeUpServerImplementation serverImplementation = new MadeUpServerImplementation( storeIdToUse )
        {
            @Override
            public Response<Void> fetchDataStream( MadeUpWriter writer, int dataSize )
            {
                writer.write( new FailingByteChannel( dataSize, failureMessage ) );
                return new TransactionStreamResponse<>( null, storeIdToUse, TransactionStream.EMPTY,
                        ResourceReleaser.NO_OP );
            }

}