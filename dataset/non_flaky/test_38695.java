class DummyClass_38695 {
    @Test
    public void testKeepNullNodes() throws Exception {
        map.put("stripNulls", false);
        sink.open(map, mockSinkContext);
        GenericRecord genericRecord = genericSchema.newRecordBuilder()
                .set("name", null)
                .set("userName", "boby")
                .set("email", null)
                .build();
        String json = sink.stringifyValue(valueSchema, genericRecord);
        assertEquals(json, "{\"name\":null,\"userName\":\"boby\",\"email\":null}");
    }

}