class DummyClass_38659 {
    @Test
    public void testNoKeyValueSchema() throws Exception {

        validateSchemaNoKeyValue(StringDeserializer.class.getName(), Schema.STRING,
                StringDeserializer.class.getName(), Schema.STRING);

        validateSchemaNoKeyValue(StringDeserializer.class.getName(), Schema.STRING,
                ByteBufferDeserializer.class.getName(), Schema.BYTEBUFFER);

        validateSchemaNoKeyValue(StringDeserializer.class.getName(), Schema.STRING,
                BytesDeserializer.class.getName(), Schema.BYTEBUFFER);

        validateSchemaNoKeyValue(StringDeserializer.class.getName(), Schema.STRING,
                DoubleDeserializer.class.getName(), Schema.DOUBLE);

        validateSchemaNoKeyValue(StringDeserializer.class.getName(), Schema.STRING,
                FloatDeserializer.class.getName(), Schema.FLOAT);

        validateSchemaNoKeyValue(StringDeserializer.class.getName(), Schema.STRING,
                IntegerDeserializer.class.getName(), Schema.INT32);

        validateSchemaNoKeyValue(StringDeserializer.class.getName(), Schema.STRING,
                LongDeserializer.class.getName(), Schema.INT64);

        validateSchemaNoKeyValue(StringDeserializer.class.getName(), Schema.STRING,
                ShortDeserializer.class.getName(), Schema.INT16);

        validateSchemaNoKeyValue(StringDeserializer.class.getName(), Schema.STRING,
                KafkaAvroDeserializer.class.getName(), KafkaBytesSource.DeferredSchemaPlaceholder.INSTANCE);

    }

}