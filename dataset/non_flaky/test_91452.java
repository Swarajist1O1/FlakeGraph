class DummyClass_91452 {
    @TestLogging(
    public void testWaitForPendingSeqNo() throws Exception {
        IndexMetaData metaData = buildIndexMetaData(1);

        final int pendingDocs = randomIntBetween(1, 5);
        final BlockingEngineFactory primaryEngineFactory = new BlockingEngineFactory();

        try (ReplicationGroup shards = new ReplicationGroup(metaData) {
            @Override
            protected EngineFactory getEngineFactory(ShardRouting routing) {
                if (routing.primary()) {
                    return primaryEngineFactory;
                } else {
                    return new InternalEngineFactory();
                }
            }
        }) {
            shards.startAll();
            int docs = shards.indexDocs(randomIntBetween(1, 10));
            // simulate a background global checkpoint sync at which point we expect the global checkpoint to advance on the replicas
            shards.syncGlobalCheckpoint();
            IndexShard replica = shards.getReplicas().get(0);
            shards.removeReplica(replica);
            closeShards(replica);

            docs += pendingDocs;
            primaryEngineFactory.latchIndexers(pendingDocs);
            CountDownLatch pendingDocsDone = new CountDownLatch(pendingDocs);
            for (int i = 0; i < pendingDocs; i++) {
                final String id = "pending_" + i;
                threadPool.generic().submit(() -> {
                    try {
                        shards.index(new IndexRequest(index.getName(), "type", id).source("{}", XContentType.JSON));
                    } catch (Exception e) {
                        throw new AssertionError(e);
                    } finally {
                        pendingDocsDone.countDown();
                    }
                });
            }

            // wait for the pending ops to "hang"
            primaryEngineFactory.awaitIndexersLatch();

            primaryEngineFactory.allowIndexing();
            // index some more
            docs += shards.indexDocs(randomInt(5));

            IndexShard newReplica = shards.addReplicaWithExistingPath(replica.shardPath(), replica.routingEntry().currentNodeId());

            CountDownLatch recoveryStart = new CountDownLatch(1);
            AtomicBoolean opsSent = new AtomicBoolean(false);
            final Future<Void> recoveryFuture = shards.asyncRecoverReplica(newReplica, (indexShard, node) -> {
                recoveryStart.countDown();
                return new RecoveryTarget(indexShard, node, recoveryListener, l -> {
                }) {
                    @Override
                    public long indexTranslogOperations(List<Translog.Operation> operations, int totalTranslogOps,
                                                        long maxSeenAutoIdTimestamp, long maxSeqNoOfUpdates) throws IOException {
                        opsSent.set(true);
                        return super.indexTranslogOperations(operations, totalTranslogOps, maxSeenAutoIdTimestamp, maxSeqNoOfUpdates);
                    }

}