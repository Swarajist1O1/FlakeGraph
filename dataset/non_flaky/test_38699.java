class DummyClass_38699 {
    @Test
    public void testGenericRecord() throws Exception {
        RecordSchemaBuilder valueSchemaBuilder = org.apache.pulsar.client.api.schema.SchemaBuilder.record("value");
        valueSchemaBuilder.field("c").type(SchemaType.STRING).optional().defaultValue(null);
        valueSchemaBuilder.field("d").type(SchemaType.INT32).optional().defaultValue(null);
        RecordSchemaBuilder udtSchemaBuilder = SchemaBuilder.record("type1");
        udtSchemaBuilder.field("a").type(SchemaType.STRING).optional().defaultValue(null);
        udtSchemaBuilder.field("b").type(SchemaType.BOOLEAN).optional().defaultValue(null);
        udtSchemaBuilder.field("d").type(SchemaType.DOUBLE).optional().defaultValue(null);
        udtSchemaBuilder.field("f").type(SchemaType.FLOAT).optional().defaultValue(null);
        udtSchemaBuilder.field("i").type(SchemaType.INT32).optional().defaultValue(null);
        udtSchemaBuilder.field("l").type(SchemaType.INT64).optional().defaultValue(null);
        GenericSchema<GenericRecord> udtGenericSchema = Schema.generic(udtSchemaBuilder.build(schemaType));
        valueSchemaBuilder.field("e", udtGenericSchema).type(schemaType).optional().defaultValue(null);
        GenericSchema<GenericRecord> valueSchema = Schema.generic(valueSchemaBuilder.build(schemaType));

        GenericRecord valueGenericRecord = valueSchema.newRecordBuilder()
                .set("c", "1")
                .set("d", 1)
                .set("e", udtGenericSchema.newRecordBuilder()
                        .set("a", "a")
                        .set("b", true)
                        .set("d", 1.0)
                        .set("f", 1.0f)
                        .set("i", 1)
                        .set("l", 10L)
                        .build())
                .build();

        Record<GenericObject> genericObjectRecord = new Record<GenericObject>() {
            @Override
            public Optional<String> getTopicName() {
                return Optional.of("data-ks1.table1");
            }

}