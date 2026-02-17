class DummyClass_38685 {
    @Test
    public void testGenericRecord() throws Exception {
        String json = "{\"c\":\"1\",\"d\":1,\"e\":{\"a\":\"a\",\"b\":true,\"d\":1.0,\"f\":1.0,\"i\":1,\"l\":10}}";

        ElasticSearchSink elasticSearchSink = new ElasticSearchSink();
        elasticSearchSink.open(ImmutableMap.of("elasticSearchUrl", "http://localhost:9200", "schemaEnable", "true"), null);
        Pair<String, String> pair = elasticSearchSink.extractIdAndDocument(new Record<GenericObject>() {
            @Override
            public GenericObject getValue() {
                return new GenericObject() {
                    @Override
                    public SchemaType getSchemaType() {
                        return SchemaType.BYTES;
                    }

}