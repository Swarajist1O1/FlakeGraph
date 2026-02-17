class DummyClass_13903 {
    @Test
    public void doRollingUpgradeFromPreviousVersionWithMasterLast() throws Throwable
    {
        /* High level scenario:
         * 1   Have a cluster of 3 instances running <old version>
         * 1.1 Download a <old version> package
         * 1.2 Unpack the <old version> package
         * 1.4 Assembly classpath and start 3 JVMs running <old version>
         * 1.5 Create some data in the cluster
         * 2   Go over each one restarting into <this version>
         * 2.1 Grab a JVM and kill it
         * 2.2 Start that db inside this test JVM, which will run <this version>
         * 2.3 Perform a write transaction to the current master and see that it picks it up
         * 2.4 Perform a write transaction to to this instance and see that master picks it up
         * 3   Make sure the cluster functions after each one has been restarted
         * 3.1 Do basic transactions on master/slaves.
         * 3.2 Do a master switch
         * 3.3 Restart one slave
         * 3.4 Take down the instances and do consistency check */

        try
        {
            startOldVersionCluster();
            rollOverToNewVersion();
            shutdownAndDoConsistencyChecks();
        }
        catch ( Throwable e )
        {
            e.printStackTrace();
            throw e;
        }
    }

}