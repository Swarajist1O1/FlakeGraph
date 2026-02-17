class DummyClass_13905 {
    @Test
    public void clientShouldReadAndApplyTransactionLogsOnNewLockSessionRequest() throws Throwable
    {
        // Given
        MasterImpl master = spy( newMasterImpl( mockMasterImplSpiWith( StoreId.DEFAULT ) ) );
        doReturn( voidResponseWithTransactionLogs() ).when( master ).newLockSession( any( RequestContext.class ) );

        cleanupRule.add( newMasterServer( master ) );

        DependencyResolver resolver = mock( DependencyResolver.class );
        LogicalTransactionStore txStore = mock( LogicalTransactionStore.class );
        TransactionRepresentationStoreApplier txApplier = mock( TransactionRepresentationStoreApplier.class );
        TransactionIdStore txIdStore = mock( TransactionIdStore.class );
        TransactionAppender txAppender = mock( TransactionAppender.class );
        when( txAppender.append( any( TransactionRepresentation.class ), anyLong() ) )
                .thenReturn( mock( Commitment.class ) );
        LogFile logFile = mock( LogFile.class );

        when( resolver.resolveDependency( LogicalTransactionStore.class ) ).thenReturn( txStore );
        when( resolver.resolveDependency( TransactionRepresentationStoreApplier.class ) ).thenReturn( txApplier );
        when( resolver.resolveDependency( TransactionIdStore.class ) ).thenReturn( txIdStore );
        when( resolver.resolveDependency( LogFile.class ) ).thenReturn( logFile );
        when( resolver.resolveDependency( LogRotation.class ) ).thenReturn( mock(LogRotation.class) );
        when( txStore.getAppender() ).thenReturn( txAppender );
        IndexUpdatesValidator indexUpdatesValidator = mock( IndexUpdatesValidator.class );
        when( indexUpdatesValidator.validate( any( TransactionRepresentation.class ),
                any( TransactionApplicationMode.class ) ) ).thenReturn( ValidatedIndexUpdates.NONE );
        when( resolver.resolveDependency( IndexUpdatesValidator.class ) ).thenReturn( indexUpdatesValidator );

        ResponseUnpacker unpacker = initAndStart( new TransactionCommittingResponseUnpacker( resolver ) );

        MasterClient masterClient = cleanupRule.add( newMasterClient214( StoreId.DEFAULT, unpacker ) );

        // When
        masterClient.newLockSession( new RequestContext( 1, 2, 3, 4, 5 ) );

        // Then
        verify( txAppender, times( TX_LOG_COUNT ) ).append( any( TransactionRepresentation.class ), anyLong() );
        // we can't verify transactionCommitted since that's part of the TransactionAppender, which we have mocked
        verify( txApplier, times( TX_LOG_COUNT ) )
                .apply( any( TransactionRepresentation.class ), any( ValidatedIndexUpdates.class ),
                        any( LockGroup.class ), anyLong(), any( TransactionApplicationMode.class ) );
        verify( txIdStore, times( TX_LOG_COUNT ) ).transactionClosed( anyLong() );
    }

}