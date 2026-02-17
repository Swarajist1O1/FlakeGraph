class DummyClass_13870 {
    @Test
    public void masterResponseShouldBeUnpackedIfRequestTypeRequires() throws IOException
    {
        // Given
        ResponseUnpacker responseUnpacker = mock( ResponseUnpacker.class );
        MadeUpClient client = builder.clientWith( responseUnpacker );
        addToLifeAndStart( builder.server(), client );

        // When
        client.multiply( 42, 42 );

        // Then
        ArgumentCaptor<Response> captor = ArgumentCaptor.forClass( Response.class );
        verify( responseUnpacker ).unpackResponse( captor.capture(), any( TxHandler.class ) );
        assertEquals( storeIdToUse, captor.getValue().getStoreId() );
        assertEquals( 42 * 42, captor.getValue().response() );
    }

}