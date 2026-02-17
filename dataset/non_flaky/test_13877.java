class DummyClass_13877 {
    @Test
    public void shouldThrowFileNotFoundExceptionWhenTryingToCopyAStoreFileWhichDoesNotExist() throws IOException
    {
        // given
        final FileSystemAbstraction fs = new StubFileSystemAbstraction();

        XaDataSource dataSource = mock( XaDataSource.class );

        FileResourceIterator storeFiles = new FileResourceIterator( fs, testDirectory, "neostore.nodestore.db" );
        storeFiles.deleteBeforeCopy( "neostore.nodestore.db" );

        FileResourceIterator logicalLogs = new FileResourceIterator( fs, testDirectory );

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
        try
        {
            ServerUtil.rotateLogsAndStreamStoreFiles( testDirectory.absolutePath(), dsManager,
                    kernelPanicEventGenerator,
                    StringLogger.DEV_NULL, true, storeWriter, fs, StoreCopyMonitor.NONE );
            fail( "should have thrown exception" );
        }
        catch ( ServerFailureException e )
        {
            // then
            assertEquals( java.io.FileNotFoundException.class, e.getCause().getClass() );
        }
    }

}