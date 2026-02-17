class DummyClass_13891 {
    @Test
    public void testAfterGentleMasterSwitchClusterInfoIsCorrect() throws Throwable
    {
        startCluster( 3 );
        RepairKit masterShutdown = cluster.shutdown( cluster.getMaster() );
        cluster.await( ClusterManager.masterAvailable() );
        cluster.await( ClusterManager.masterSeesSlavesAsAvailable( 1 ) );
        for ( HighlyAvailableGraphDatabase db : cluster.getAllMembers() )
        {
            assertEquals( 2, ha( db ).getInstancesInCluster().length );
        }
        masterShutdown.repair();
        cluster.await( ClusterManager.allSeesAllAsAvailable() );
        for ( HighlyAvailableGraphDatabase db : cluster.getAllMembers() )
        {
            HighAvailability bean = ha( db );

            assertEquals( 3, bean.getInstancesInCluster().length );
            for ( ClusterMemberInfo info : bean.getInstancesInCluster() )
            {
                assertTrue( "every instance should be available", info.isAvailable() );
                assertTrue( "every instances should have at least one role", info.getRoles().length > 0 );
                if ( HighAvailabilityModeSwitcher.MASTER.equals( info.getRoles()[0] ) )
                {
                    assertEquals( "coordinator should be master",
                            HighAvailabilityModeSwitcher.MASTER, info.getHaRole() );
                }
                else
                {
                    assertEquals( "Either master or slave, no other way",
                            HighAvailabilityModeSwitcher.SLAVE, info.getRoles()[0] );
                    assertEquals( "instance " + info.getInstanceId() + " is cluster slave but HA master",
                            HighAvailabilityModeSwitcher.SLAVE, info.getHaRole() );
                }
                for ( String uri : info.getUris() )
                {
                    assertTrue( "roles should contain URIs",
                            uri.startsWith( "ha://" ) || uri.startsWith( "backup://" ) );
                }
            }
        }
    }

}