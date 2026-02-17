class DummyClass_13898 {
    @Test
    public void makeSureBackupCanBePerformedFromWronglyNamedCluster() throws Throwable
    {
        assertEquals( 0, runBackupToolFromOtherJvmToGetExitCode(
                backupArguments( "localhost:4445", BACKUP_PATH.getPath(), "non.existent" ) ) );
    }

}