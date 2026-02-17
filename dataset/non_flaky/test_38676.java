class DummyClass_38676 {
    @Test
    public void testOpenAndWrite() throws Exception {
        message = mock(MessageImpl.class);
        GenericSchema<GenericRecord> genericAvroSchema;
        // prepare a cpu Record
        Cpu cpu = new Cpu();
        cpu.setMeasurement("cpu");
        cpu.setModel("lenovo");
        cpu.setValue(10);

        Map<String, String> tags = Maps.newHashMap();
        tags.put("host", "server-1");
        tags.put("region", "us-west");

        cpu.setTags(tags);
        AvroSchema<Cpu> schema = AvroSchema.of(Cpu.class);

        byte[] bytes = schema.encode(cpu);
        AutoConsumeSchema autoConsumeSchema = new AutoConsumeSchema();
        autoConsumeSchema.setSchema(GenericSchemaImpl.of(schema.getSchemaInfo()));

        Record<GenericRecord> record = PulsarRecord.<GenericRecord>builder()
            .message(message)
            .topicName("influx_cpu")
            .build();

        genericAvroSchema = new GenericAvroSchema(schema.getSchemaInfo());

        when(message.getValue())
                .thenReturn(genericAvroSchema.decode(bytes));

        log.info("cpu:{}, Message.getValue: {}, record.getValue: {}",
            cpu.toString(),
            message.getValue().toString(),
            record.getValue().toString());

        influxSink.open(configMap, mockSinkContext);

        verify(this.influxDB, times(1)).describeDatabases();
        verify(this.influxDB, times(1)).createDatabase("testDB");

        doAnswer(invocationOnMock -> {
            BatchPoints batchPoints = invocationOnMock.getArgument(0, BatchPoints.class);
            Assert.assertNotNull(batchPoints, "batchPoints should not be null.");
            return null;
        }).when(influxDB).write(any(BatchPoints.class));

        influxSink.write(record);

        Thread.sleep(1000);

        verify(influxDB, times(1)).write(any(BatchPoints.class));
    }

}