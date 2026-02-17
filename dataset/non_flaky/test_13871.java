class DummyClass_13871 {
    @Test
    public void masterResponseShouldNotBeUnpackedIfRequestTypeDoesNotRequire()
    {
        // Given
        ResponseUnpacker responseUnpacker = mock( ResponseUnpacker.class );
        MadeUpClient client = builder.clientWith( responseUnpacker );
        addToLifeAndStart( builder.server(), client );

        // When
        client.sendDataStream( new KnownDataByteChannel( 100 ) );

        // Then
        verifyZeroInteractions( responseUnpacker );
    }

}