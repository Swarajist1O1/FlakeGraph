class DummyClass_91451 {
    @TestLogging("org.elasticsearch.index.shard:TRACE,org.elasticsearch.action.resync:TRACE")
    public void testResyncAfterPrimaryPromotion() throws Exception {
        // TODO: check translog trimming functionality once rollback is implemented in Lucene (ES trimming is done)
        Map<String, String> mappings =
            Collections.singletonMap("type", "{ \"type\": { \"properties\": { \"f\": { \"type\": \"keyword\"} }}}");
        try (ReplicationGroup shards = new ReplicationGroup(buildIndexMetaData(2, mappings))) {
            shards.startAll();
            int initialDocs = randomInt(10);

            for (int i = 0; i < initialDocs; i++) {
                final IndexRequest indexRequest = new IndexRequest(index.getName(), "type", "initial_doc_" + i)
                    .source("{ \"f\": \"normal\"}", XContentType.JSON);
                shards.index(indexRequest);
            }

            boolean syncedGlobalCheckPoint = randomBoolean();
            if (syncedGlobalCheckPoint) {
                shards.syncGlobalCheckpoint();
            }

            final IndexShard oldPrimary = shards.getPrimary();
            final IndexShard newPrimary = shards.getReplicas().get(0);
            final IndexShard justReplica = shards.getReplicas().get(1);

            // simulate docs that were inflight when primary failed
            final int extraDocs = randomInt(5);
            logger.info("--> indexing {} extra docs", extraDocs);
            for (int i = 0; i < extraDocs; i++) {
                final IndexRequest indexRequest = new IndexRequest(index.getName(), "type", "extra_doc_" + i)
                    .source("{ \"f\": \"normal\"}", XContentType.JSON);
                final BulkShardRequest bulkShardRequest = indexOnPrimary(indexRequest, oldPrimary);
                indexOnReplica(bulkShardRequest, shards, newPrimary);
            }

            final int extraDocsToBeTrimmed = randomIntBetween(0, 10);
            logger.info("--> indexing {} extra docs to be trimmed", extraDocsToBeTrimmed);
            for (int i = 0; i < extraDocsToBeTrimmed; i++) {
                final IndexRequest indexRequest = new IndexRequest(index.getName(), "type", "extra_trimmed_" + i)
                    .source("{ \"f\": \"trimmed\"}", XContentType.JSON);
                final BulkShardRequest bulkShardRequest = indexOnPrimary(indexRequest, oldPrimary);
                // have to replicate to another replica != newPrimary one - the subject to trim
                indexOnReplica(bulkShardRequest, shards, justReplica);
            }

            logger.info("--> resyncing replicas seqno_stats primary {} replica {}", oldPrimary.seqNoStats(), newPrimary.seqNoStats());
            PrimaryReplicaSyncer.ResyncTask task = shards.promoteReplicaToPrimary(newPrimary).get();
            if (syncedGlobalCheckPoint) {
                assertEquals(extraDocs, task.getResyncedOperations());
            } else {
                assertThat(task.getResyncedOperations(), greaterThanOrEqualTo(extraDocs));
            }
            shards.assertAllEqual(initialDocs + extraDocs);
            for (IndexShard replica : shards.getReplicas()) {
                assertThat(replica.getMaxSeqNoOfUpdatesOrDeletes(),
                    greaterThanOrEqualTo(shards.getPrimary().getMaxSeqNoOfUpdatesOrDeletes()));
            }

            // check translog on replica is trimmed
            int translogOperations = 0;
            try(Translog.Snapshot snapshot = getTranslog(justReplica).newSnapshot()) {
                Translog.Operation next;
                while ((next = snapshot.next()) != null) {
                    translogOperations++;
                    assertThat("unexpected op: " + next, (int)next.seqNo(), lessThan(initialDocs + extraDocs));
                    assertThat("unexpected primaryTerm: " + next.primaryTerm(), next.primaryTerm(),
                        is(oldPrimary.getPendingPrimaryTerm()));
                    final Translog.Source source = next.getSource();
                    assertThat(source.source.utf8ToString(), is("{ \"f\": \"normal\"}"));
                }
            }
            assertThat(translogOperations, is(initialDocs + extraDocs));
        }
    }

}