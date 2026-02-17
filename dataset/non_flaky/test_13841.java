class DummyClass_13841 {
    @Test
    public void shouldNotApplyTransactionIfIndexUpdatesValidationFails() throws Throwable
    {
        // Given
        DependencyResolver resolver = mock( DependencyResolver.class );

        when( resolver.resolveDependency( LogFile.class ) ).thenReturn( mock( LogFile.class ) );
        when( resolver.resolveDependency( LogRotation.class ) ).thenReturn( mock( LogRotation.class ) );
        when( resolver.resolveDependency( TransactionIdStore.class ) ).thenReturn( mock( TransactionIdStore.class ) );
        KernelHealth kernelHealth = mock( KernelHealth.class );
        when( resolver.resolveDependency( KernelHealth.class ) ).thenReturn( kernelHealth );
        LogicalTransactionStore txStore = mock( LogicalTransactionStore.class );
        TransactionAppender appender = mockedTransactionAppender();
        when( txStore.getAppender() ).thenReturn( appender );
        when( resolver.resolveDependency( LogicalTransactionStore.class ) ).thenReturn( txStore );
        TransactionRepresentationStoreApplier storeApplier = mock( TransactionRepresentationStoreApplier.class );
        when( resolver.resolveDependency( TransactionRepresentationStoreApplier.class ) ).thenReturn( storeApplier );

        IndexUpdatesValidator validator = mock( IndexUpdatesValidator.class );
        IOException error = new IOException( "error" );
        when( validator.validate( any( TransactionRepresentation.class ), eq( TransactionApplicationMode.EXTERNAL ) ) )
                .thenThrow( error );
        when( resolver.resolveDependency( IndexUpdatesValidator.class ) ).thenReturn( validator );

        TransactionCommittingResponseUnpacker unpacker = new TransactionCommittingResponseUnpacker( resolver );
        unpacker.start();

        Response<?> response = new DummyTransactionResponse( BASE_TX_ID + 1, 1, appender, 10 );

        // When
        try
        {
            unpacker.unpackResponse( response, NO_OP_TX_HANDLER );
            fail( "Should have thrown " + IOException.class.getSimpleName() );
        }
        catch ( IOException e )
        {
            assertSame( error, e );
        }

        // Then
        verifyZeroInteractions( storeApplier );
        verify( kernelHealth ).panic( error );
    }

}