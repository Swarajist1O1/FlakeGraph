class DummyClass_91453 {
    @TestLogging(
    public void testCheckpointsAndMarkingInSync() throws Exception {
        final IndexMetaData metaData = buildIndexMetaData(0);
        final BlockingEngineFactory replicaEngineFactory = new BlockingEngineFactory();
        try (
                ReplicationGroup shards = new ReplicationGroup(metaData) {
                    @Override
                    protected EngineFactory getEngineFactory(final ShardRouting routing) {
                        if (routing.primary()) {
                            return new InternalEngineFactory();
                        } else {
                            return replicaEngineFactory;
                        }
                    }
                };
                AutoCloseable ignored = replicaEngineFactory // make sure we release indexers before closing
        ) {
            shards.startPrimary();
            final int docs = shards.indexDocs(randomIntBetween(1, 10));
            logger.info("indexed [{}] docs", docs);
            final CountDownLatch pendingDocDone = new CountDownLatch(1);
            final CountDownLatch pendingDocActiveWithExtraDocIndexed = new CountDownLatch(1);
            final CountDownLatch phaseTwoStartLatch = new CountDownLatch(1);
            final IndexShard replica = shards.addReplica();
            final Future<Void> recoveryFuture = shards.asyncRecoverReplica(
                    replica,
                    (indexShard, node) -> new RecoveryTarget(indexShard, node, recoveryListener, l -> {}) {
                        @Override
                        public long indexTranslogOperations(final List<Translog.Operation> operations, final int totalTranslogOps,
                                                            final long maxAutoIdTimestamp, long maxSeqNoOfUpdates)

}