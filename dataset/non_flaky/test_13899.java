class DummyClass_13899 {
    @Test
    public void makeSureBackupCanBeRestored() throws Throwable
    {
        // Run backup
        assertEquals( 0, runBackupToolFromOtherJvmToGetExitCode( backupArguments( "localhost:4445",
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