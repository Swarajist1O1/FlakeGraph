class DummyClass_13842 {
    @Test
    public void shouldNotMarkTransactionsAsCommittedIfAppenderClosed() throws Throwable
    {
        // GIVEN an unpacker with close-to-real dependencies injected
        DependencyResolver resolver = mock( DependencyResolver.class );
        // (we don't want this FS in every test in this class, so just don't use EFSR)
        FileSystemAbstraction fs = cleanup.add( new EphemeralFileSystemAbstraction() );
        File directory = new File( "dir" );
        fs.mkdirs( directory );
        PhysicalLogFiles logFiles = new PhysicalLogFiles( directory, fs );
        TransactionIdStore transactionIdStore = spy( new DeadSimpleTransactionIdStore() );
        LogVersionRepository logVersionRepository = mock( LogVersionRepository.class );
        TransactionMetadataCache transactionMetadataCache = new TransactionMetadataCache( 10, 10 );
        LogFile logFile = life.add( new PhysicalLogFile( fs, logFiles, 1_000, transactionIdStore,
                logVersionRepository, new PhysicalLogFile.Monitor.Adapter(), transactionMetadataCache ) );
        KernelHealth health = mock( KernelHealth.class );
        LogRotation logRotation = LogRotation.NO_ROTATION;
        LogicalTransactionStore logicalTransactionStore = life.add( new PhysicalLogicalTransactionStore( logFile,
                logRotation, transactionMetadataCache, transactionIdStore, IdOrderingQueue.BYPASS,
                health, true ) );
        IndexUpdatesValidator indexUpdatesValidator = mock( IndexUpdatesValidator.class );
        when( indexUpdatesValidator.validate( any( TransactionRepresentation.class ),
                any( TransactionApplicationMode.class ) ) ).thenReturn( ValidatedIndexUpdates.NONE );
        life.start();
        TransactionAppender appender = logicalTransactionStore.getAppender();
        when( resolver.resolveDependency( LogicalTransactionStore.class ) ).thenReturn( logicalTransactionStore );
        when( resolver.resolveDependency( IndexUpdatesValidator.class ) ).thenReturn( indexUpdatesValidator );
        when( resolver.resolveDependency( TransactionIdStore.class ) ).thenReturn( transactionIdStore );
        when( resolver.resolveDependency( TransactionObligationFulfiller.class ) ).thenReturn( null );
        when( resolver.resolveDependency( LogFile.class ) ).thenReturn( logFile );
        when( resolver.resolveDependency( LogRotation.class ) ).thenReturn( logRotation );
        when( resolver.resolveDependency( KernelHealth.class ) ).thenReturn( health );
        when( resolver.resolveDependency( TransactionObligationFulfiller.class ) ).thenThrow(
                new IllegalArgumentException() );
        TransactionCommittingResponseUnpacker unpacker = new TransactionCommittingResponseUnpacker( resolver );
        unpacker.start();

        // and a closed logFile/appender
        life.shutdown();

        // WHEN packing up a transaction response
        try
        {
            unpacker.unpackResponse( new DummyTransactionResponse( BASE_TX_ID+1, 1, appender, 5 ), NO_OP_TX_HANDLER );
            fail( "Should have failed" );
        }
        catch ( Exception e )
        {
            // THEN apart from failing we don't want any committed/closed calls to TransactionIdStore
            verify( transactionIdStore, times( 0 ) ).transactionCommitted( anyLong(), anyLong() );
            verify( transactionIdStore, times( 0 ) ).transactionClosed( anyLong() );
        }
    }

}