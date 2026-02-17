class DummyClass_13839 {
    @Test
    public void shouldAwaitTransactionObligationsToBeFulfilled() throws Throwable
    {
        // GIVEN
        DependencyResolver dependencyResolver = mock( DependencyResolver.class );

        TransactionIdStore txIdStore = mock( TransactionIdStore.class );
        when( dependencyResolver.resolveDependency( TransactionIdStore.class ) ).thenReturn( txIdStore );

        TransactionAppender appender = mock( TransactionAppender.class );
        LogicalTransactionStore logicalTransactionStore = mock( LogicalTransactionStore.class );
        when( logicalTransactionStore.getAppender() ).thenReturn( appender );
        when( dependencyResolver.resolveDependency( LogicalTransactionStore.class ) )
                .thenReturn( logicalTransactionStore );

        when( dependencyResolver.resolveDependency( TransactionRepresentationStoreApplier.class ) )
                .thenReturn( mock( TransactionRepresentationStoreApplier.class ) );
        TransactionObligationFulfiller obligationFulfiller = mock( TransactionObligationFulfiller.class );
        when( dependencyResolver.resolveDependency( TransactionObligationFulfiller.class ) )
                .thenReturn( obligationFulfiller );
        final TransactionCommittingResponseUnpacker unpacker = new TransactionCommittingResponseUnpacker(
                dependencyResolver );
        unpacker.start();

        // WHEN
        unpacker.unpackResponse( new DummyObligationResponse( 4 ), NO_OP_TX_HANDLER );

        // THEN
        verify( obligationFulfiller, times( 1 ) ).fulfill( 4l );
    }

}