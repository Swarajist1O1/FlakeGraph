class DummyClass_38672 {
    @Test
    public void testOpenWriteCloseAvro() throws Exception {
        AvroSchema<Cpu> avroSchema = AvroSchema.of(Cpu.class);
        openWriteClose(avroSchema);
    }

}