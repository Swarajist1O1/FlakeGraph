class DummyClass_38623 {
    @Test
    public void TestUpdateAction() throws Exception {

        AvroSchema<Foo> schema = AvroSchema.of(SchemaDefinition.<Foo>builder().withPojo(Foo.class).build());

        Foo updateObj = new Foo();
        updateObj.setField1("ValueOfField3");
        updateObj.setField2("ValueOfField3");
        updateObj.setField3(4);

        byte[] updateBytes = schema.encode(updateObj);
        Message<GenericRecord> updateMessage = mock(MessageImpl.class);
        CompletableFuture<Void> future = new CompletableFuture<>();
        Record<GenericRecord> updateRecord = PulsarRecord.<GenericRecord>builder()
                .message(updateMessage)
                .topicName("fake_topic_name")
                .ackFunction(() -> future.complete(null))
                .build();

        GenericSchema<GenericRecord> updateGenericAvroSchema;
        updateGenericAvroSchema = new GenericAvroSchema(schema.getSchemaInfo());

        Map<String, String> updateProperties = Maps.newHashMap();
        updateProperties.put("ACTION", "UPDATE");
        when(updateMessage.getValue()).thenReturn(updateGenericAvroSchema.decode(updateBytes));
        when(updateMessage.getProperties()).thenReturn(updateProperties);
        log.info("foo:{}, Message.getValue: {}, record.getValue: {}",
                updateObj.toString(),
                updateMessage.getValue().toString(),
                updateRecord.getValue().toString());

        jdbcSink.write(updateRecord);
        future.get(1, TimeUnit.SECONDS);

        // value has been written to db, read it out and verify.
        String updateQuerySql = "SELECT * FROM " + tableName + " WHERE field3=4";
        sqliteUtils.select(updateQuerySql, (resultSet) -> {
            Assert.assertEquals(updateObj.getField1(), resultSet.getString(1));
            Assert.assertEquals(updateObj.getField2(), resultSet.getString(2));
            Assert.assertEquals(updateObj.getField3(), resultSet.getInt(3));
        });
    }

}