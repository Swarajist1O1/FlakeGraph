class DummyClass_13834 {
    @Test
    public void shouldHaveFixedTargetTransactionIdEvenIfLastTransactionIdIsMoving() throws Exception
    {
        // GIVEN
        LogicalTransactionStore transactionStore = mock( LogicalTransactionStore.class );
        long lastAppliedTransactionId = 5L;
        IOCursor<CommittedTransactionRepresentation> endlessCursor = new EndlessCursor( lastAppliedTransactionId+1 );
        when( transactionStore.getTransactions( anyLong() ) ).thenReturn( endlessCursor );
        final long targetTransactionId = 8L;
        final TransactionIdStore transactionIdStore = new DeadSimpleTransactionIdStore( targetTransactionId, 0 );
        ResponsePacker packer = new ResponsePacker( transactionStore, transactionIdStore,
                singletonProvider( new StoreId() ) );

        // WHEN
        Response<Object> response = packer.packTransactionStreamResponse( requestContextStartingAt( 5L ), null );
        final AtomicLong nextExpectedVisit = new AtomicLong( lastAppliedTransactionId );
        response.accept( new Response.Handler()
        {
            @Override
            public void obligation( long txId ) throws IOException
            {
                fail( "Should not be called" );
            }

}