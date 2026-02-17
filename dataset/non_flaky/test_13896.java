class DummyClass_13896 {
    @Test
    public void failedMemberIsStillInMemberListAlthoughFailed() throws Throwable
    {
        startCluster( 3 );
        assertEquals( 3, ha( cluster.getAnySlave() ).getInstancesInCluster().length );

        // Fail the instance
        HighlyAvailableGraphDatabase failedDb = cluster.getAnySlave();
        RepairKit dbFailure = cluster.fail( failedDb );
        await( ha( cluster.getMaster() ), dbAlive( false ) );
        await( ha( cluster.getAnySlave( failedDb )), dbAlive( false ) );

        // Repair the failure and come back
        dbFailure.repair();
        for ( HighlyAvailableGraphDatabase db : cluster.getAllMembers() )
        {
            await( ha( db ), dbAvailability( true ) );
            await( ha( db ), dbAlive( true ) );
        }
    }

}