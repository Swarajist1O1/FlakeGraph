class DummyClass_91485 {
@TestLogging("_root:DEBUG,org.elasticsearch.action.admin.indices.shards:TRACE,org.elasticsearch.cluster.service:TRACE")
    public void testEmpty() {
        ensureGreen();
        IndicesShardStoresResponse rsp = client().admin().indices().prepareShardStores().get();
        assertThat(rsp.getStoreStatuses().size(), equalTo(0));
    }

}