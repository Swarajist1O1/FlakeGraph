class DummyClass_38680 {
    @Test
    public void testLogicalTypesToJson() {
        Schema decimalType = LogicalTypes.decimal(3,3).addToSchema(Schema.create(Schema.Type.BYTES));
        Schema dateType = LogicalTypes.date().addToSchema(Schema.create(Schema.Type.INT));
        Schema timestampMillisType = LogicalTypes.timestampMillis().addToSchema(Schema.create(Schema.Type.LONG));
        Schema timestampMicrosType = LogicalTypes.timestampMicros().addToSchema(Schema.create(Schema.Type.LONG));
        Schema timeMillisType = LogicalTypes.timeMillis().addToSchema(Schema.create(Schema.Type.INT));
        Schema timeMicrosType = LogicalTypes.timeMicros().addToSchema(Schema.create(Schema.Type.LONG));
        Schema uuidType = LogicalTypes.uuid().addToSchema(Schema.create(Schema.Type.STRING));
        Schema schema = SchemaBuilder.record("record")
                .fields()
                .name("amount").type(decimalType).noDefault()
                .name("mydate").type(dateType).noDefault()
                .name("tsmillis").type(timestampMillisType).noDefault()
                .name("tsmicros").type(timestampMicrosType).noDefault()
                .name("timemillis").type(timeMillisType).noDefault()
                .name("timemicros").type(timeMicrosType).noDefault()
                .name("myuuid").type(uuidType).noDefault()
                .endRecord();

        final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
        BigDecimal myDecimal = new BigDecimal("10.34");
        UUID myUuid = UUID.randomUUID();
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("Europe/Copenhagen"));
        GenericRecord genericRecord = new GenericData.Record(schema);
        genericRecord.put("amount", myDecimal);
        genericRecord.put("mydate", (int)calendar.toInstant().getEpochSecond());
        genericRecord.put("tsmillis", calendar.getTimeInMillis());
        genericRecord.put("tsmicros", calendar.getTimeInMillis() * 1000);
        genericRecord.put("timemillis", (int)(calendar.getTimeInMillis() % MILLIS_PER_DAY));
        genericRecord.put("timemicros", (calendar.getTimeInMillis() %MILLIS_PER_DAY) * 1000);
        genericRecord.put("myuuid", myUuid.toString());
        JsonNode jsonNode = JsonConverter.toJson(genericRecord);
        assertEquals(new BigDecimal(jsonNode.get("amount").asText()), myDecimal);
        assertEquals(jsonNode.get("mydate").asInt(), calendar.toInstant().getEpochSecond());
        assertEquals(jsonNode.get("tsmillis").asInt(), (int)calendar.getTimeInMillis());
        assertEquals(jsonNode.get("tsmicros").asLong(), calendar.getTimeInMillis() * 1000);
        assertEquals(jsonNode.get("timemillis").asInt(), (int)(calendar.getTimeInMillis() % MILLIS_PER_DAY));
        assertEquals(jsonNode.get("timemicros").asLong(), (calendar.getTimeInMillis() %MILLIS_PER_DAY) * 1000);
        assertEquals(UUID.fromString(jsonNode.get("myuuid").asText()), myUuid);
    }

}