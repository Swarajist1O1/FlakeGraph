class DummyClass_70849 {
    @Test
    public void testHeaders() throws Exception {
        Headers headers = new RecordHeaders();
        headers.add("header_key", "header_value".getBytes());

        org.apache.kafka.connect.header.Headers connectHeaders = new ConnectHeaders();
        connectHeaders.add("header_key", new SchemaAndValue(Schema.STRING_SCHEMA, "header_value"));

        createWorkerTask();

        List<SourceRecord> records = new ArrayList<>();
        records.add(new SourceRecord(PARTITION, OFFSET, "topic", null, KEY_SCHEMA, KEY, RECORD_SCHEMA, RECORD, null, connectHeaders));

        Capture<ProducerRecord<byte[], byte[]>> sent = expectSendRecord(true, false, true, true, true, headers);

        PowerMock.replayAll();

        Whitebox.setInternalState(workerTask, "toSend", records);
        Whitebox.invokeMethod(workerTask, "sendRecords");
        assertEquals(SERIALIZED_KEY, sent.getValue().key());
        assertEquals(SERIALIZED_RECORD, sent.getValue().value());
        assertEquals(headers, sent.getValue().headers());

        PowerMock.verifyAll();
    }

}