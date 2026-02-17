class DummyClass_70842 {
    @Test
    public void testSendRecordsNoTimestamp() throws Exception {
        final Long timestamp = -1L;
        createWorkerTask();

        List<SourceRecord> records = Collections.singletonList(
                new SourceRecord(PARTITION, OFFSET, "topic", null, KEY_SCHEMA, KEY, RECORD_SCHEMA, RECORD, timestamp)
        );

        Capture<ProducerRecord<byte[], byte[]>> sent = expectSendRecordAnyTimes();

        PowerMock.replayAll();

        Whitebox.setInternalState(workerTask, "toSend", records);
        Whitebox.invokeMethod(workerTask, "sendRecords");
        assertEquals(null, sent.getValue().timestamp());

        PowerMock.verifyAll();
    }

}