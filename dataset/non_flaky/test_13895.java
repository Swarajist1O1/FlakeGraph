class DummyClass_13895 {
    @Test
    public void leftInstanceDisappearsFromMemberList() throws Throwable
    {
        // Start the second db and make sure it's visible in the member list.
        // Then shut it down to see if it disappears from the member list again.
        startCluster( 3 );
        assertEquals( 3, ha( cluster.getAnySlave() ).getInstancesInCluster().length );
        cluster.shutdown( cluster.getAnySlave() );

        cluster.await( masterSeesMembers( 2 ) );

        assertEquals( 2, ha( cluster.getMaster() ).getInstancesInCluster().length );
        assertMasterInformation( ha( cluster.getMaster() ) );
    }

}