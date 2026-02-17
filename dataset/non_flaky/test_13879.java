class DummyClass_13879 {
    @Test
    public void shouldSerializeAndDeserializeTransactionRepresentation() throws Exception
    {
        // GIVEN
        PhysicalTransactionRepresentation transaction = new PhysicalTransactionRepresentation( justOneNode() );
        byte[] additionalHeader = "extra".getBytes();
        int masterId = 1, authorId = 2;
        long timeStarted = 12345, lastTxWhenStarted = 12, timeCommitted = timeStarted+10;
        transaction.setHeader( additionalHeader, masterId, authorId, timeStarted, lastTxWhenStarted, timeCommitted, -1 );
        Protocol.TransactionSerializer serializer = new Protocol.TransactionSerializer( transaction );
        ChannelBuffer buffer = new ChannelBufferWrapper( new InMemoryLogChannel() );

        // WHEN serializing the transaction
        serializer.write( buffer );

        // THEN deserializing the same transaction should yield the same data.
        // ... remember that this deserializer doesn't read the data source name string. Read it manually here
        assertEquals( NeoStoreDataSource.DEFAULT_DATA_SOURCE_NAME, Protocol.readString( buffer ) );
        TransactionRepresentation readTransaction = Protocol.TRANSACTION_REPRESENTATION_DESERIALIZER.read(
                buffer, ByteBuffer.allocate( 1000 ) );
        assertArrayEquals( additionalHeader, readTransaction.additionalHeader() );
        assertEquals( masterId, readTransaction.getMasterId() );
        assertEquals( authorId, readTransaction.getAuthorId() );
        assertEquals( timeStarted, readTransaction.getTimeStarted() );
        assertEquals( lastTxWhenStarted, readTransaction.getLatestCommittedTxWhenStarted() );
        assertEquals( timeCommitted, readTransaction.getTimeCommitted() );
    }

}