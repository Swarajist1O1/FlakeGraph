class DummyClass_13837 {
    @Test
    public void testStopShouldAllowTransactionsToCompleteCommitAndApply() throws Throwable
    {
        // Given

        // Handcrafted deep mocks, otherwise the dependency resolution throws ClassCastExceptions
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
        LogFile logFile = mock( LogFile.class );
        when( dependencyResolver.resolveDependency( LogFile.class ) ).thenReturn( logFile );
        LogRotation logRotation = mock(LogRotation.class);
        when( dependencyResolver.resolveDependency( LogRotation.class ) ).thenReturn( logRotation );

        setUpIndexUpdatesValidatorMocking( dependencyResolver );

          /*
           * The tx handler is called on every transaction applied after setting its id to committing
           * but before setting it to applied. We use this to stop the unpacker in the middle of the
           * process.
           */
        StoppingTxHandler stoppingTxHandler = new StoppingTxHandler();

        int maxBatchSize = 10;
        TransactionCommittingResponseUnpacker unpacker = new TransactionCommittingResponseUnpacker(
                dependencyResolver, maxBatchSize );
        stoppingTxHandler.setUnpacker( unpacker );

        // When
        unpacker.start();
        long committingTransactionId = BASE_TX_ID + 1;
        DummyTransactionResponse response = new DummyTransactionResponse( committingTransactionId, 1, appender, maxBatchSize );
        unpacker.unpackResponse( response, stoppingTxHandler );

        // Then
        // we can't verify transactionCommitted since that's part of the TransactionAppender, which we have mocked
        verify( txIdStore, times( 1 ) ).transactionClosed( committingTransactionId );
        verify( appender, times( 1 ) ).append( any( TransactionRepresentation.class ), anyLong() );
        verify( appender, times( 1 ) ).force();
        verify( logRotation, times( 1 ) ).rotateLogIfNeeded( logAppendEvent );

        // Then
          // The txhandler has stopped the unpacker. It should not allow any more transactions to go through
        try
        {
            unpacker.unpackResponse( mock( Response.class ), stoppingTxHandler );
            fail( "A stopped transaction unpacker should not allow transactions to be applied" );
        }
        catch( IllegalStateException e)
        {
            // good
        }
        verifyNoMoreInteractions( txIdStore );
        verifyNoMoreInteractions( appender );
    }

}