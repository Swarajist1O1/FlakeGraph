class DummyClass_91468 {
    @TestLogging(
    public void testMappingTimeout() throws Exception {
        startCluster(3);
        createIndex("test", Settings.builder()
            .put("index.number_of_shards", 1)
            .put("index.number_of_replicas", 1)
            .put("index.routing.allocation.exclude._name", internalCluster().getMasterName())
        .build());

        // create one field
        index("test", "doc", "1", "{ \"f\": 1 }");

        ensureGreen();

        assertAcked(client().admin().cluster().prepareUpdateSettings().setTransientSettings(
            Settings.builder().put("indices.mapping.dynamic_timeout", "1ms")));

        ServiceDisruptionScheme disruption = new BlockMasterServiceOnMaster(random());
        setDisruptionScheme(disruption);

        disruption.startDisrupting();

        BulkRequestBuilder bulk = client().prepareBulk();
        bulk.add(client().prepareIndex("test", "doc", "2").setSource("{ \"f\": 1 }", XContentType.JSON));
        bulk.add(client().prepareIndex("test", "doc", "3").setSource("{ \"g\": 1 }", XContentType.JSON));
        bulk.add(client().prepareIndex("test", "doc", "4").setSource("{ \"f\": 1 }", XContentType.JSON));
        BulkResponse bulkResponse = bulk.get();
        assertTrue(bulkResponse.hasFailures());

        disruption.stopDisrupting();

        assertBusy(() -> {
            IndicesStatsResponse stats = client().admin().indices().prepareStats("test").clear().get();
            for (ShardStats shardStats : stats.getShards()) {
                assertThat(shardStats.getShardRouting().toString(),
                    shardStats.getSeqNoStats().getGlobalCheckpoint(), equalTo(shardStats.getSeqNoStats().getLocalCheckpoint()));
            }
        });

    }

}