class DummyClass_13880 {
    @Test
    public void shouldMigrate() throws IOException, ConsistencyCheckIncompleteException
    {
        // WHEN
        upgrader( new StoreMigrator( monitor, fs, DevNullLoggingService.DEV_NULL ) )
                .migrateIfNeeded(
                find20FormatStoreDirectory( storeDir.directory() ), schemaIndexProvider, pageCache );

        // THEN
        assertEquals( 100, monitor.eventSize() );
        assertTrue( monitor.isStarted() );
        assertTrue( monitor.isFinished() );

        GraphDatabaseService database = new GraphDatabaseFactory().newEmbeddedDatabase( storeDir.absolutePath() );
        try
        {
            verifyDatabaseContents( database );
        }
        finally
        {
            // CLEANUP
            database.shutdown();
        }

        try ( NeoStore neoStore = storeFactory.newNeoStore( true ) )
        {
            verifyNeoStore( neoStore );
        }
        assertConsistentStore( storeDir.directory() );
    }

}