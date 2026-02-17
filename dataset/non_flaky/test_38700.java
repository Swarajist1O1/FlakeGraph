class DummyClass_38700 {
    @Test
    public void testKeyValueGenericRecord() throws Exception {
        RecordSchemaBuilder keySchemaBuilder = org.apache.pulsar.client.api.schema.SchemaBuilder.record("key");
        keySchemaBuilder.field("a").type(SchemaType.STRING).optional().defaultValue(null);
        keySchemaBuilder.field("b").type(SchemaType.INT32).optional().defaultValue(null);
        GenericSchema<GenericRecord> keySchema = Schema.generic(keySchemaBuilder.build(schemaType));
        GenericRecord keyGenericRecord = keySchema.newRecordBuilder()
                .set("a", "1")
                .set("b", 1)
                .build();

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

        Schema<KeyValue<GenericRecord, GenericRecord>> keyValueSchema = Schema.KeyValue(keySchema, valueSchema, KeyValueEncodingType.INLINE);
        KeyValue<GenericRecord, GenericRecord> keyValue = new KeyValue<>(keyGenericRecord, valueGenericRecord);
        GenericObject genericObject = new GenericObject() {
            @Override
            public SchemaType getSchemaType() {
                return SchemaType.KEY_VALUE;
            }

}