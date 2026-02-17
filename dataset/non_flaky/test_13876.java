class DummyClass_13876 {
    @Test
    public void shouldNotThrowFileNotFoundExceptionWhenTryingToCopyAMissingLogicalLogFile() throws IOException
    {
        // given
        final FileSystemAbstraction fs = new StubFileSystemAbstraction();

        XaDataSource dataSource = mock( XaDataSource.class );

        FileResourceIterator storeFiles = new FileResourceIterator( fs, testDirectory, "neostore.nodestore.db" );

        FileResourceIterator logicalLogs = new FileResourceIterator( fs, testDirectory,
        PhysicalLogFile.DEFAULT_NAME + PhysicalLogFile.DEFAULT_VERSION_SUFFIX + "0" );
        logicalLogs.deleteBeforeCopy( PhysicalLogFile.DEFAULT_NAME + PhysicalLogFile.DEFAULT_VERSION_SUFFIX + "0" );

        when( dataSource.listStoreFiles() ).thenReturn( storeFiles );
        when( dataSource.listLogicalLogs() ).thenReturn( logicalLogs );

        when( dataSource.getBranchId() ).thenReturn( "branch".getBytes() );
        when( dataSource.getName() ).thenReturn( "branch" );

        XaContainer xaContainer = mock( XaContainer.class );
        when( dataSource.getXaContainer() ).thenReturn( xaContainer );

        XaResourceManager xaResourceManager = mock( XaResourceManager.class );
        when( xaContainer.getResourceManager() ).thenReturn( xaResourceManager );

        XaDataSourceManager dsManager = new XaDataSourceManager( StringLogger.DEV_NULL );
        dsManager.registerDataSource( dataSource );

        KernelPanicEventGenerator kernelPanicEventGenerator = mock( KernelPanicEventGenerator.class );
        StoreWriter storeWriter = mock( StoreWriter.class );

        // when
        ServerUtil.rotateLogsAndStreamStoreFiles( testDirectory.absolutePath(), dsManager, kernelPanicEventGenerator,
                StringLogger.DEV_NULL, true, storeWriter, fs, StoreCopyMonitor.NONE );

        // then
        verify( storeWriter ).write( eq( "neostore.nodestore.db" ), any( ReadableByteChannel.class ),
                any( ByteBuffer.class ), any( Boolean.class ) );
    }

}