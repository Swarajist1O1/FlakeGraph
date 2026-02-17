class DummyClass_91450 {
    @TestLogging("org.elasticsearch.index.shard:TRACE,org.elasticsearch.indices.recovery:TRACE")
    public void testRecoveryAfterPrimaryPromotion() throws Exception {
        try (ReplicationGroup shards = createGroup(2)) {
            shards.startAll();
            int totalDocs = shards.indexDocs(randomInt(10));
            int committedDocs = 0;
            if (randomBoolean()) {
                shards.flush();
                committedDocs = totalDocs;
            }

            final IndexShard oldPrimary = shards.getPrimary();
            final IndexShard newPrimary = shards.getReplicas().get(0);
            final IndexShard replica = shards.getReplicas().get(1);
            if (randomBoolean()) {
                // simulate docs that were inflight when primary failed, these will be rolled back
                final int rollbackDocs = randomIntBetween(1, 5);
                logger.info("--> indexing {} rollback docs", rollbackDocs);
                for (int i = 0; i < rollbackDocs; i++) {
                    final IndexRequest indexRequest = new IndexRequest(index.getName(), "type", "rollback_" + i)
                            .source("{}", XContentType.JSON);
                    final BulkShardRequest bulkShardRequest = indexOnPrimary(indexRequest, oldPrimary);
                    indexOnReplica(bulkShardRequest, shards, replica);
                }
                if (randomBoolean()) {
                    oldPrimary.flush(new FlushRequest(index.getName()));
                }
            }

            shards.promoteReplicaToPrimary(newPrimary).get();

            // check that local checkpoint of new primary is properly tracked after primary promotion
            assertThat(newPrimary.getLocalCheckpoint(), equalTo(totalDocs - 1L));
            assertThat(IndexShardTestCase.getReplicationTracker(newPrimary)
                .getTrackedLocalCheckpointForShard(newPrimary.routingEntry().allocationId().getId()).getLocalCheckpoint(),
                equalTo(totalDocs - 1L));

            // index some more
            int moreDocs = shards.indexDocs(randomIntBetween(0, 5));
            totalDocs += moreDocs;

            // As a replica keeps a safe commit, the file-based recovery only happens if the required translog
            // for the sequence based recovery are not fully retained and extra documents were added to the primary.
            boolean expectSeqNoRecovery = (moreDocs == 0 || randomBoolean());
            int uncommittedOpsOnPrimary = 0;
            if (expectSeqNoRecovery == false) {
                IndexMetaData.Builder builder = IndexMetaData.builder(newPrimary.indexSettings().getIndexMetaData());
                builder.settings(Settings.builder().put(newPrimary.indexSettings().getSettings())
                    .put(IndexSettings.INDEX_TRANSLOG_RETENTION_AGE_SETTING.getKey(), "-1")
                    .put(IndexSettings.INDEX_TRANSLOG_RETENTION_SIZE_SETTING.getKey(), "-1")
                    .put(IndexSettings.INDEX_SOFT_DELETES_RETENTION_OPERATIONS_SETTING.getKey(), 0)
                );
                newPrimary.indexSettings().updateIndexMetaData(builder.build());
                newPrimary.onSettingsChanged();
                // Make sure the global checkpoint on the new primary is persisted properly,
                // otherwise the deletion policy won't trim translog
                assertBusy(() -> {
                    shards.syncGlobalCheckpoint();
                    assertThat(newPrimary.getLastSyncedGlobalCheckpoint(), equalTo(newPrimary.seqNoStats().getMaxSeqNo()));
                });
                newPrimary.flush(new FlushRequest().force(true));
                if (replica.indexSettings().isSoftDeleteEnabled()) {
                    // We need an extra flush to advance the min_retained_seqno on the new primary so ops-based won't happen.
                    // The min_retained_seqno only advances when a merge asks for the retention query.
                    newPrimary.flush(new FlushRequest().force(true));
                }
                uncommittedOpsOnPrimary = shards.indexDocs(randomIntBetween(0, 10));
                totalDocs += uncommittedOpsOnPrimary;
            }

            if (randomBoolean()) {
                uncommittedOpsOnPrimary = 0;
                shards.syncGlobalCheckpoint();
                newPrimary.flush(new FlushRequest());
            }

            oldPrimary.close("demoted", false);
            oldPrimary.store().close();

            IndexShard newReplica = shards.addReplicaWithExistingPath(oldPrimary.shardPath(), oldPrimary.routingEntry().currentNodeId());
            shards.recoverReplica(newReplica);

            if (expectSeqNoRecovery) {
                assertThat(newReplica.recoveryState().getIndex().fileDetails(), empty());
                assertThat(newReplica.recoveryState().getTranslog().recoveredOperations(), equalTo(totalDocs - committedDocs));
            } else {
                assertThat(newReplica.recoveryState().getIndex().fileDetails(), not(empty()));
                assertThat(newReplica.recoveryState().getTranslog().recoveredOperations(), equalTo(uncommittedOpsOnPrimary));
            }
            // Make sure that flushing on a recovering shard is ok.
            shards.flush();
            shards.assertAllEqual(totalDocs);
        }
    }

}