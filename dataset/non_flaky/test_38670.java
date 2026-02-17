class DummyClass_38670 {
    @Test
    public void testJsonSchema() {
        JSONSchema<Cpu> schema = JSONSchema.of(Cpu.class);

        AutoConsumeSchema autoConsumeSchema = new AutoConsumeSchema();
        autoConsumeSchema.setSchema(GenericSchemaImpl.of(schema.getSchemaInfo()));
        GenericSchema<GenericRecord> genericSchema = GenericSchemaImpl.of(autoConsumeSchema.getSchemaInfo());

        assertFalse(genericSchema instanceof GenericAvroSchema);

        byte[] bytes = schema.encode(cpu);
        GenericRecord record = genericSchema.decode(bytes);

        assertEquals(record.getField("measurement"), "cpu");

        // compare the String type
        assertEquals(record.getField("timestamp").toString(), timestamp + "");

        assertEquals(((GenericRecord)record.getField("tags")).getField("host"), "server-1");
        assertEquals(((GenericRecord)record.getField("fields")).getField("value"), 10);
    }

}