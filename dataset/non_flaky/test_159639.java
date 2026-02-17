class DummyClass_159639 {
    @Test
    public void testDatabaseIsReachableIfRequired() {
        if (isDatabaseProvidedByTravisCI()) {
            assertNotNull(
                    "This integration test is expected to pass on Travis CI.\n" +
                            "If you are running on a dev machine and do not have the required\n" +
                            "database installed, you may choose to ignore this failed test.\n" +
                            "To run this test on a dev machine, you will need to install the corresponding\n" +
                            "database and configure liquibase.integrationtest.local.properties",
                    getDatabase());
        } else {
            assumeNotNull(this.getDatabase());
        }
    }

}