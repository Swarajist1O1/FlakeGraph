class DummyClass_13927 {
    @Test
    public void twoStatesWithSameSetupAndPendingMessagesShouldBeEqual() throws Exception
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
        ClusterState firstState = state.performAction( new MessageDeliveryAction( Message.to( ClusterMessage.join,
                new URI( "cluster://localhost:5002" ), new Object[]{"defaultcluster",
                        new URI[]{new URI( "cluster://localhost:5003" )}} ).setHeader( Message.CONVERSATION_ID,
                "-1" ).setHeader( Message.FROM, "cluster://localhost:5002" ) ) );
        ClusterState secondState = state.performAction( new MessageDeliveryAction( Message.to( ClusterMessage.join,
                new URI( "cluster://localhost:5002" ), new Object[]{"defaultcluster",
                        new URI[]{new URI( "cluster://localhost:5003" )}} ).setHeader( Message.CONVERSATION_ID,
                "-1" ).setHeader( Message.FROM, "cluster://localhost:5002" ) ) );

        // Then
        assertEquals( firstState, secondState );
        assertEquals( firstState.hashCode(), secondState.hashCode() );
    }

}