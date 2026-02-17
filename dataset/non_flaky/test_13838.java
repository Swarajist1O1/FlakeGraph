class DummyClass_13838 {
    @Test
    public void shouldApplyQueuedTransactionsIfMany() throws Throwable
    {
        // GIVEN
        DependencyResolver dependencyResolver = mock( DependencyResolver.class );
        TransactionIdStore txIdStore = mock( TransactionIdStore.class );

        when( dependencyResolver.resolveDependency( TransactionIdStore.class ) ).thenReturn( txIdStore );

        TransactionAppender appender = mockedTransactionAppender();
        LogicalTransactionStore logicalTransactionStore = mock( LogicalTransactionStore.class );
        when( logicalTransactionStore.getAppender() ).thenReturn( appender );
        when( dependencyResolver.resolveDependency( LogicalTransactionStore.class ) )
                .thenReturn( logicalTransactionStore );

        when( dependencyResolver.resolveDependency( TransactionRepresentationStoreApplier.class ) )
                .thenReturn( mock( TransactionRepresentationStoreApplier.class ) );

        setUpIndexUpdatesValidatorMocking( dependencyResolver );

        LogFile logFile = mock( LogFile.class );
        when( dependencyResolver.resolveDependency( LogFile.class ) ).thenReturn( logFile );

        LogRotation logRotation = mock(LogRotation.class);
        when( dependencyResolver.resolveDependency( LogRotation.class ) ).thenReturn( logRotation );

        int maxBatchSize = 3;
        TransactionCommittingResponseUnpacker unpacker = new TransactionCommittingResponseUnpacker(
                dependencyResolver, maxBatchSize );
        unpacker.start();

        // WHEN/THEN
        int txCount = maxBatchSize * 2 - 1;
        unpacker.unpackResponse( new DummyTransactionResponse( 2, txCount, appender, maxBatchSize ), NO_OP_TX_HANDLER );

        // and THEN
        verify( appender, times( txCount ) ).append( any( TransactionRepresentation.class ), anyLong() );
        verify( appender, times( 2 ) ).force();
        verify( logRotation, times( 2 ) ).rotateLogIfNeeded( logAppendEvent );
    }

}