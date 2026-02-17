class DummyClass_38673 {
    @Test
    public void testOpenWriteCloseJson() throws Exception {
        JSONSchema<Cpu> jsonSchema = JSONSchema.of(Cpu.class);
        openWriteClose(jsonSchema);
    }

}