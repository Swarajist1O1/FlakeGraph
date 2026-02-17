class DummyClass_13885 {
    @Test
    public void shouldDeduplicateUniquePropertyIndexKeys() throws Exception
    {
        // GIVEN
        // a store that contains two nodes with property "name" of which there are two key tokens
        File legacyStoreDir = find19FormatStoreDirectory( storeDir.directory() );

        // WHEN
        // upgrading that store, the two key tokens for "name" should be merged

        newStoreUpgrader().migrateIfNeeded( storeDir.directory(), schemaIndexProvider, pageCache );

        // THEN
        // verify that the "name" property for both the involved nodes
        GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase( storeDir.absolutePath() );
        try
        {
            Node nodeA = getNodeWithName( db, "A" );
            assertThat( nodeA, inTx( db, hasProperty( "name" ).withValue( "A" ) ) );

            Node nodeB = getNodeWithName( db, "B" );
            assertThat( nodeB, inTx( db, hasProperty( "name" ).withValue( "B" ) ) );

            Node nodeC = getNodeWithName( db, "C" );
            assertThat( nodeC, inTx( db, hasProperty( "name" ).withValue( "C" ) ) );
            assertThat( nodeC, inTx( db, hasProperty( "other" ).withValue( "a value" ) ) );
            assertThat( nodeC, inTx( db, hasProperty( "third" ).withValue( "something" ) ) );
        }
        finally
        {
            db.shutdown();
        }

        // THEN
        // verify that there are no duplicate keys in the store
        try ( PropertyKeyTokenStore tokenStore = storeFactory.newPropertyKeyTokenStore() )
        {
            Token[] tokens = tokenStore.getTokens( MAX_VALUE );
            assertNoDuplicates( tokens );
        }

        assertConsistentStore( storeDir.directory() );
    }

}