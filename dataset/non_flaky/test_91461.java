class DummyClass_91461 {
@TestLogging("_root:DEBUG,org.elasticsearch.cluster.service:TRACE")
    public void testIsolatedUnicastNodes() throws Exception {
        List<String> nodes = startCluster(4, -1, new int[]{0});
        // Figure out what is the elected master node
        final String unicastTarget = nodes.get(0);

        Set<String> unicastTargetSide = new HashSet<>();
        unicastTargetSide.add(unicastTarget);

        Set<String> restOfClusterSide = new HashSet<>();
        restOfClusterSide.addAll(nodes);
        restOfClusterSide.remove(unicastTarget);

        // Forcefully clean temporal response lists on all nodes. Otherwise the node in the unicast host list
        // includes all the other nodes that have pinged it and the issue doesn't manifest
        ZenPing zenPing = ((TestZenDiscovery) internalCluster().getInstance(Discovery.class)).getZenPing();
        if (zenPing instanceof UnicastZenPing) {
            ((UnicastZenPing) zenPing).clearTemporalResponses();
        }

        // Simulate a network issue between the unicast target node and the rest of the cluster
        NetworkDisruption networkDisconnect = new NetworkDisruption(new TwoPartitions(unicastTargetSide, restOfClusterSide),
                new NetworkDisconnect());
        setDisruptionScheme(networkDisconnect);
        networkDisconnect.startDisrupting();
        // Wait until elected master has removed that the unlucky node...
        ensureStableCluster(3, nodes.get(1));

        // The isolate master node must report no master, so it starts with pinging
        assertNoMaster(unicastTarget);
        networkDisconnect.stopDisrupting();
        // Wait until the master node sees all 3 nodes again.
        ensureStableCluster(4);
    }

}