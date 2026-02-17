class DummyClass_38651 {
    @Test
    public void TestOpenAndWriteSink() throws Exception {
        message = mock(MessageImpl.class);
        Map<String, Object> configs = new HashMap<>();
        configs.put("solrUrl", "http://localhost:8983/solr");
        configs.put("solrMode", "Standalone");
        configs.put("solrCollection", "techproducts");
        configs.put("solrCommitWithinMs", "100");
        configs.put("username", "");
        configs.put("password", "");
        GenericSchema<GenericRecord> genericAvroSchema;

        SolrGenericRecordSink sink = new SolrGenericRecordSink();

        // prepare a foo Record
        Foo obj = new Foo();
        obj.setField1("FakeFiled1");
        obj.setField2("FakeFiled1");
        AvroSchema<Foo> schema = AvroSchema.of(Foo.class);

        byte[] bytes = schema.encode(obj);
        AutoConsumeSchema autoConsumeSchema = new AutoConsumeSchema();
        autoConsumeSchema.setSchema(GenericSchemaImpl.of(schema.getSchemaInfo()));

        Record<GenericRecord> record = PulsarRecord.<GenericRecord>builder()
            .message(message)
            .topicName("fake_topic_name")
            .build();

        genericAvroSchema = new GenericAvroSchema(schema.getSchemaInfo());

        when(message.getValue())
                .thenReturn(genericAvroSchema.decode(bytes));

        log.info("foo:{}, Message.getValue: {}, record.getValue: {}",
            obj.toString(),
            message.getValue().toString(),
            record.getValue().toString());

        // open should success
        sink.open(configs, null);
    }

}