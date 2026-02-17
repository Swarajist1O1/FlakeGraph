class DummyClass_13894 {
    @Test
    public void joinedInstanceShowsUpAsSlave() throws Throwable
    {
        startCluster( 2 );
        ClusterMemberInfo[] instancesInCluster = ha( cluster.getMaster() ).getInstancesInCluster();
        assertEquals( 2, instancesInCluster.length );
        ClusterMemberInfo[] secondInstancesInCluster = ha( cluster.getAnySlave() ).getInstancesInCluster();
        assertEquals( 2, secondInstancesInCluster.length );
        assertMasterAndSlaveInformation( instancesInCluster );
        assertMasterAndSlaveInformation( secondInstancesInCluster );
    }

}