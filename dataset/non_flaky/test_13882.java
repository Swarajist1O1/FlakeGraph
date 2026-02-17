class DummyClass_13882 {
    @Test
    public void mustMendDuplicatePropertiesWhenUpgradingFromVersion21() throws Exception
    {
        // The rules:
        // If an index is present, all duplicates should be removed and the property set to the value in the index
        // If an index is not present, the property should be set to the value of the last duplicate in the property
        // chain, all duplicates except the first should be removed
        // If an index is not present, the first property in the duplicate chain should be kept for the users
        // benefit, moved to a special property value, `__DUPLICATE_<propkey>`
        //
        // This is the broken store that we are upgrading:
        //
        //   (#0:Label { keyA: "actual", keyA: "phony!", keyA: "phony!" })
        //   (#1 { keyA: "actual", keyA: "actual", keyA: "actual" })
        //   (#2:Label { keyA: "real1", keyA: "phony", keyA: "phony", keyD: "real2", keyD: "phony", keyD: "phony" })
        //   (#3 { keyA: "real1", keyA: "phony", keyA: "phony", keyD: "real2", keyD: "phony", keyD: "phony" })
        //   (#4 { keyA: "actual", keyB: "actual", keyC: "actual" })
        //   (#0)-[#0:REL { keyA: "actual", keyA: "actual", keyA: "actual" }]->(#1)
        //   (#0)-[#1:REL { keyA: "real1", keyA: "phony", keyA: "phony",
        //                  keyD: "real2", keyE: "phony", keyF: "phony" }]->(#1)
        //   (#2)-[#2:REL { keyA: "actual", keyB: "actual", keyC: "actual" }]->(#0)
        //
        // And this is what we want to end up with, after upgrading:
        //
        //   (#0:Label { keyA: "actual" })
        //   (#1 { keyA: "actual", __DUPLICATE_keyA: "actual" })
        //   (#2:Label { keyA: "real1", keyD: "real2" })
        //   (#3 { keyA: "real1", __DUPLICATE_keyA_1: "real1", __DUPLICATE_keyA_2: "real1",
        //         keyD: "real2", __DUPLICATE_keyD_1: "real2", __DUPLICATE_keyD_2: "real2" })
        //   (#4 { keyA: "actual", keyB: "actual", keyC: "actual" })
        //   (#0)-[#0:REL { keyA: "actual", __DUPLICATE_keyA: "actual" }]->(#1)
        //   (#0)-[#1:REL { keyA: "real1", __DUPLICATE_keyA_1: "real1", __DUPLICATE_keyA_2: "real1",
        //                  keyD: "real2", __DUPLICATE_keyD_1: "real2", __DUPLICATE_keyD_2: "real2" }]->(#1)
        //   (#2)-[#2:REL { keyA: "actual", keyB: "actual", keyC: "actual" }]->(#0)

        File dir = MigrationTestUtils.find21FormatStoreDirectoryWithDuplicateProperties( storeDir.directory() );

        GraphDatabaseBuilder builder =
                new GraphDatabaseFactory().newEmbeddedDatabaseBuilder( dir.getAbsolutePath() ).setConfig(
                        GraphDatabaseSettings.allow_store_upgrade, "true" );
        GraphDatabaseService database = builder.newGraphDatabase();
        database.shutdown();
        ConsistencyCheckService service = new ConsistencyCheckService();

        ConsistencyCheckService.Result result = service.runFullConsistencyCheck(
                dir.getAbsolutePath(), new Config(), ProgressMonitorFactory.NONE, StringLogger.SYSTEM );
        assertTrue( result.isSuccessful() );

        database = builder.newGraphDatabase();
        // Upgrade is now completed. Verify the contents:
        DependencyResolver dependencyResolver = ((GraphDatabaseAPI) database).getDependencyResolver();
        NeoStoreProvider provider = dependencyResolver.resolveDependency( NeoStoreProvider.class );
        NeoStore store = provider.evaluate();
        NodeStore nodeStore = store.getNodeStore();
        RelationshipStore relStore = store.getRelationshipStore();
        PropertyStore propertyStore = store.getPropertyStore();

        // Verify that the properties appear correct to the outside world:
        try ( Transaction ignore = database.beginTx() )
        {
            verifyPropertiesEqual( database.getNodeById( 0 ),
                    Pair.of( "keyA", "actual" ) );
            verifyPropertiesEqual( database.getNodeById( 1 ),
                    Pair.of( "keyA", "actual" ),
                    Pair.of( "__DUPLICATE_keyA_1", "actual" ),
                    Pair.of( "__DUPLICATE_keyA_2", "actual" ));
            verifyPropertiesEqual( database.getNodeById( 2 ),
                    Pair.of( "keyA", "real1" ),
                    Pair.of( "keyD", "real2" ) );
            verifyPropertiesEqual( database.getNodeById( 3 ),
                    Pair.of( "keyA", "real1" ),
                    Pair.of( "__DUPLICATE_keyA_1", "real1" ),
                    Pair.of( "__DUPLICATE_keyA_2", "real1" ),
                    Pair.of( "keyD", "real2" ),
                    Pair.of( "__DUPLICATE_keyD_1", "real2" ),
                    Pair.of( "__DUPLICATE_keyD_2", "real2" ) );
            verifyPropertiesEqual( database.getNodeById( 4 ),
                    Pair.of( "keyA", "actual" ),
                    Pair.of( "keyB", "actual" ),
                    Pair.of( "keyC", "actual" ) );
            verifyPropertiesEqual( database.getRelationshipById( 0 ),
                    Pair.of( "keyA", "actual" ),
                    Pair.of( "__DUPLICATE_keyA_1", "actual" ),
                    Pair.of( "__DUPLICATE_keyA_2", "actual" ));
            verifyPropertiesEqual( database.getRelationshipById( 1 ),
                    Pair.of( "keyA", "real1" ),
                    Pair.of( "__DUPLICATE_keyA_1", "real1" ),
                    Pair.of( "__DUPLICATE_keyA_2", "real1" ),
                    Pair.of( "keyD", "real2" ),
                    Pair.of( "__DUPLICATE_keyD_1", "real2" ),
                    Pair.of( "__DUPLICATE_keyD_2", "real2" ) );
            verifyPropertiesEqual( database.getRelationshipById( 2 ),
                    Pair.of( "keyA", "actual" ),
                    Pair.of( "keyB", "actual" ),
                    Pair.of( "keyC", "actual" ) );
        }

        // Verify that there are no two properties on the entities, that have the same key:
        // (This is important because the verification above cannot tell if we have two keys with the same value)
        verifyNoDuplicatePropertyKeys( propertyStore, nodeStore.getRecord( 0 ).getNextProp() );
        verifyNoDuplicatePropertyKeys( propertyStore, nodeStore.getRecord( 1 ).getNextProp() );
        verifyNoDuplicatePropertyKeys( propertyStore, nodeStore.getRecord( 2 ).getNextProp() );
        verifyNoDuplicatePropertyKeys( propertyStore, relStore.getRecord( 0 ).getNextProp() );
        verifyNoDuplicatePropertyKeys( propertyStore, relStore.getRecord( 1 ).getNextProp() );

        database.shutdown();
    }

}