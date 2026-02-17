class DummyClass_13900 {
    @Test
    public void makeSureBackupCanBePerformedFromAnyInstance() throws Throwable
    {
        Integer[] backupPorts = {4445, 4446, 4447};

        for ( Integer port : backupPorts )
        {
            // Run backup
            assertEquals( 0, runBackupToolFromOtherJvmToGetExitCode( backupArguments( "localhost:" + port,
                    BACKUP_PATH.getPath(), null ) ) );

            // Add some new data
            DbRepresentation changedData = createSomeData( cluster.getMaster() );

            stopCluster();

            cleanData();

            copyBackup();

            startCluster();

            // Verify that old data is back
            assertThat( changedData.equals( DbRepresentation.of( cluster.getMaster() ) ), equalTo(false) );
        }
    }

}