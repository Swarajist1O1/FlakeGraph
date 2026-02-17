class DummyClass_91464 {
@TestLogging("org.elasticsearch.discovery.zen.publish:TRACE")
        public MockNode setAsMaster() {
            this.clusterState = ClusterState.builder(clusterState).nodes(DiscoveryNodes.builder(clusterState.nodes())
                .masterNodeId(discoveryNode.getId())).build();
            return this;
        }

}