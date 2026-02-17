class DummyClass_38660 {
    @Test
    public void testKeyValueSchema() throws Exception {
        validateSchemaKeyValue(IntegerDeserializer.class.getName(), Schema.INT32,
                StringDeserializer.class.getName(), Schema.STRING,
                ByteBuffer.wrap(new IntegerSerializer().serialize("test", 10)),
                ByteBuffer.wrap(new StringSerializer().serialize("test", "test")));
    }

}