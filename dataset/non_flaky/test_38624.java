class DummyClass_38624 {
    @Test
    public void TestDeleteAction() throws Exception {

        AvroSchema<Foo> schema = AvroSchema.of(SchemaDefinition.<Foo>builder().withPojo(Foo.class).build());

        Foo deleteObj = new Foo();
        deleteObj.setField3(5);

        byte[] deleteBytes = schema.encode(deleteObj);
        Message<GenericRecord> deleteMessage = mock(MessageImpl.class);
        CompletableFuture<Void> future = new CompletableFuture<>();
        Record<GenericRecord> deleteRecord = PulsarRecord.<GenericRecord>builder()
                .message(deleteMessage)
                .topicName("fake_topic_name")
                .ackFunction(() -> future.complete(null))
                .build();

        GenericSchema<GenericRecord> deleteGenericAvroSchema = new GenericAvroSchema(schema.getSchemaInfo());

        Map<String, String> deleteProperties = Maps.newHashMap();
        deleteProperties.put("ACTION", "DELETE");
        when(deleteMessage.getValue()).thenReturn(deleteGenericAvroSchema.decode(deleteBytes));
        when(deleteMessage.getProperties()).thenReturn(deleteProperties);
        log.info("foo:{}, Message.getValue: {}, record.getValue: {}",
                deleteObj.toString(),
                deleteMessage.getValue().toString(),
                deleteRecord.getValue().toString());

        jdbcSink.write(deleteRecord);
        future.get(1, TimeUnit.SECONDS);

        // value has been written to db, read it out and verify.
        String deleteQuerySql = "SELECT * FROM " + tableName + " WHERE field3=5";
        Assert.assertEquals(sqliteUtils.select(deleteQuerySql, (resultSet) -> {}), 0);
    }

}