class DummyClass_38671 {
    @Test
    public void testAvroSchema() {
        AvroSchema<Cpu> schema = AvroSchema.of(Cpu.class);

        AutoConsumeSchema autoConsumeSchema = new AutoConsumeSchema();
        autoConsumeSchema.setSchema(GenericSchemaImpl.of(schema.getSchemaInfo()));
        GenericSchema<GenericRecord> genericAvroSchema = GenericSchemaImpl.of(autoConsumeSchema.getSchemaInfo());

        assertTrue(genericAvroSchema instanceof GenericAvroSchema);

        byte[] bytes = schema.encode(cpu);
        GenericRecord record = genericAvroSchema.decode(bytes);

        assertEquals(record.getField("measurement"), "cpu");
        assertEquals(record.getField("timestamp"), timestamp);
        assertEquals(((Map)record.getField("tags")).get(new Utf8("host")).toString(), "server-1");
        assertEquals(((Map)record.getField("fields")).get(new Utf8("value")), 10);
    }

}