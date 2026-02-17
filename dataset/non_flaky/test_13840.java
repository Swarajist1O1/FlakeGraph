class DummyClass_13840 {
    @Test
    public void shouldIssueKernelPanicInCaseOfFailureToAppendOrApply() throws Throwable
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
        LogFile logFile = mock( LogFile.class );
        when( dependencyResolver.resolveDependency( LogFile.class ) ).thenReturn( logFile );
        KernelHealth kernelHealth = mock( KernelHealth.class );
        when( dependencyResolver.resolveDependency( KernelHealth.class ) ).thenReturn( kernelHealth );
        LogRotation logRotation = mock(LogRotation.class);
        when( dependencyResolver.resolveDependency( LogRotation.class ) ).thenReturn( logRotation );
        final TransactionCommittingResponseUnpacker unpacker = new TransactionCommittingResponseUnpacker(
                dependencyResolver );
        unpacker.start();

        // WHEN failing to append one or more transactions from a transaction stream response
        IOException failure = new IOException( "Expected failure" );
        doThrow( failure ).when( appender ).append( any( TransactionRepresentation.class ), anyLong() );
        try
        {
            unpacker.unpackResponse(
                    new DummyTransactionResponse( BASE_TX_ID+1, 1, appender, 10 ), NO_OP_TX_HANDLER );
            fail( "Should have failed" );
        }
        catch ( IOException e )
        {
            assertThat( e.getMessage(), containsString( failure.getMessage() ) );
            verify( kernelHealth ).panic( failure );
        }
    }

}