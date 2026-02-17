class DummyClass_13926 {
    @Test
    public void aClusterSnapshotShouldEqualItsOrigin() throws Exception
    {
        // Given
        Logging logging = new TestLogging();
        ClusterConfiguration config = new ClusterConfiguration( "default",
                logging.getMessagesLog( ClusterConfiguration.class ),
                "cluster://localhost:5001",
                "cluster://localhost:5002",
                "cluster://localhost:5003" );

        ClusterState state = new ClusterState(
                asList(
                        newClusterInstance( new InstanceId( 1 ), new URI( "cluster://localhost:5001" ),
                                new Monitors(), config, logging ),
                        newClusterInstance( new InstanceId( 2 ), new URI( "cluster://localhost:5002" ),
                                new Monitors(), config, logging ),
                        newClusterInstance( new InstanceId( 3 ), new URI( "cluster://localhost:5003" ),
                                new Monitors(), config, logging ) ),
                emptySetOf( ClusterAction.class )
        );

        // When
        ClusterState snapshot = state.snapshot();

        // Then
        assertEquals( state, snapshot );
        assertEquals( state.hashCode(), snapshot.hashCode() );
    }

}