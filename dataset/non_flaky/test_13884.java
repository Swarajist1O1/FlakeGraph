class DummyClass_13884 {
    @Test
    public void shouldMigrateCluster() throws Throwable
    {
        // Given
        File legacyStoreDir = find19FormatHugeStoreDirectory( storeDir.directory() );

        // When
        newStoreUpgrader().migrateIfNeeded( legacyStoreDir, schemaIndexProvider, pageCache );

        ClusterManager.ManagedCluster cluster = buildClusterWithMasterDirIn( fs, legacyStoreDir, life );
        cluster.await( allSeesAllAsAvailable() );
        cluster.sync();

        // Then
        HighlyAvailableGraphDatabase slave1 = cluster.getAnySlave();
        verifySlaveContents( slave1 );
        verifySlaveContents( cluster.getAnySlave( slave1 ) );
        verifyDatabaseContents( cluster.getMaster() );
    }

}