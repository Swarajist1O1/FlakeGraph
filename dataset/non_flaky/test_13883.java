class DummyClass_13883 {
    @Test
    public void shouldMigrate() throws IOException, ConsistencyCheckIncompleteException
    {
        // GIVEN
        File legacyStoreDir = find19FormatHugeStoreDirectory( storeDir.directory() );

        // WHEN
        newStoreUpgrader().migrateIfNeeded( legacyStoreDir, schemaIndexProvider, pageCache );

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