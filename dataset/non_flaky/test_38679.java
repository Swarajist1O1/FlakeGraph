class DummyClass_38679 {
    @Test
    public void testAvroToJson() throws IOException {
        Schema schema = SchemaBuilder.record("record").fields()
                .name("n").type().longType().longDefault(10)
                .name("l").type().longType().longDefault(10)
                .name("i").type().intType().intDefault(10)
                .name("b").type().booleanType().booleanDefault(true)
                .name("bb").type().bytesType().bytesDefault("10")
                .name("d").type().doubleType().doubleDefault(10.0)
                .name("f").type().floatType().floatDefault(10.0f)
                .name("s").type().stringType().stringDefault("titi")
                .name("array").type().optional().array().items(SchemaBuilder.builder().stringType())
                .name("map").type().optional().map().values(SchemaBuilder.builder().intType())
                .endRecord();
        GenericRecord genericRecord = new GenericData.Record(schema);
        genericRecord.put("n", null);
        genericRecord.put("l", 1L);
        genericRecord.put("i", 1);
        genericRecord.put("b", true);
        genericRecord.put("bb", "10".getBytes(StandardCharsets.UTF_8));
        genericRecord.put("d", 10.0);
        genericRecord.put("f", 10.0f);
        genericRecord.put("s", "toto");
        genericRecord.put("array", new String[] {"toto"});
        genericRecord.put("map", ImmutableMap.of("a",10));
        JsonNode jsonNode = JsonConverter.toJson(genericRecord);
        assertEquals(jsonNode.get("n"), NullNode.getInstance());
        assertEquals(jsonNode.get("l").asLong(), 1L);
        assertEquals(jsonNode.get("i").asInt(), 1);
        assertEquals(jsonNode.get("b").asBoolean(), true);
        assertEquals(jsonNode.get("bb").binaryValue(), "10".getBytes(StandardCharsets.UTF_8));
        assertEquals(jsonNode.get("d").asDouble(), 10.0);
        assertEquals(jsonNode.get("f").numberValue(), 10.0f);
        assertEquals(jsonNode.get("s").asText(), "toto");
        assertTrue(jsonNode.get("array").isArray());
        assertEquals(jsonNode.get("array").iterator().next().asText(), "toto");
        assertTrue(jsonNode.get("map").isObject());
        assertEquals(jsonNode.get("map").elements().next().asText(), "10");
        assertEquals(jsonNode.get("map").get("a").numberValue(), 10);
    }

}