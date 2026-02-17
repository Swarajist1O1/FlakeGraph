class DummyClass_13892 {
    @Test
    public void testAfterHardMasterSwitchClusterInfoIsCorrect() throws Throwable
    {
        startCluster( 3 );
        RepairKit masterShutdown = cluster.fail( cluster.getMaster() );
        cluster.await( ClusterManager.masterAvailable() );
        cluster.await( ClusterManager.masterSeesSlavesAsAvailable( 1 ) );
        for ( HighlyAvailableGraphDatabase db : cluster.getAllMembers() )
        {
            if ( db.getInstanceState() == HighAvailabilityMemberState.PENDING )
            {
                continue;
            }
            // Instance that was hard killed will still be in the cluster
            assertEquals( 3, ha( db ).getInstancesInCluster().length );
        }
        masterShutdown.repair();
        cluster.await( ClusterManager.masterAvailable() );
        cluster.await( ClusterManager.masterSeesSlavesAsAvailable( 2 ) );
        for ( HighlyAvailableGraphDatabase db : cluster.getAllMembers() )
        {
            int mastersFound = 0;
            HighAvailability bean = ha( db );

            assertEquals( 3, bean.getInstancesInCluster().length );
            for ( ClusterMemberInfo info : bean.getInstancesInCluster() )
            {
                assertTrue( bean.getInstanceId() + ": every instance should be available: " + info.getInstanceId(),
                        info.isAvailable() );
                for ( String role : info.getRoles() )
                {
                    if (role.equals( HighAvailabilityModeSwitcher.MASTER ))
                    {
                        mastersFound++;
                    }
                }
            }
            assertEquals( 1, mastersFound );
        }
    }

}