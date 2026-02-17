class DummyClass_38694 {
    @Test
    public void testStripNullNodes() throws Exception {
        map.put("stripNulls", true);
        sink.open(map, mockSinkContext);
        GenericRecord genericRecord = genericSchema.newRecordBuilder()
                .set("name", null)
                .set("userName", "boby")
                .set("email", null)
                .build();
        String json = sink.stringifyValue(valueSchema, genericRecord);
        assertEquals(json, "{\"userName\":\"boby\"}");
    }

}