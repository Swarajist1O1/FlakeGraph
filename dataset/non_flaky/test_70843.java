class DummyClass_70843 {
    @Test
    public void testSendRecordsRetries() throws Exception {
        createWorkerTask();

        // Differentiate only by Kafka partition so we can reuse conversion expectations
        SourceRecord record1 = new SourceRecord(PARTITION, OFFSET, "topic", 1, KEY_SCHEMA, KEY, RECORD_SCHEMA, RECORD);
        SourceRecord record2 = new SourceRecord(PARTITION, OFFSET, "topic", 2, KEY_SCHEMA, KEY, RECORD_SCHEMA, RECORD);
        SourceRecord record3 = new SourceRecord(PARTITION, OFFSET, "topic", 3, KEY_SCHEMA, KEY, RECORD_SCHEMA, RECORD);

        // First round
        expectSendRecordOnce(false);
        // Any Producer retriable exception should work here
        expectSendRecordSyncFailure(new org.apache.kafka.common.errors.TimeoutException("retriable sync failure"));

        // Second round
        expectSendRecordOnce(true);
        expectSendRecordOnce(false);

        PowerMock.replayAll();

        // Try to send 3, make first pass, second fail. Should save last two
        Whitebox.setInternalState(workerTask, "toSend", Arrays.asList(record1, record2, record3));
        Whitebox.invokeMethod(workerTask, "sendRecords");
        assertEquals(true, Whitebox.getInternalState(workerTask, "lastSendFailed"));
        assertEquals(Arrays.asList(record2, record3), Whitebox.getInternalState(workerTask, "toSend"));

        // Next they all succeed
        Whitebox.invokeMethod(workerTask, "sendRecords");
        assertEquals(false, Whitebox.getInternalState(workerTask, "lastSendFailed"));
        assertNull(Whitebox.getInternalState(workerTask, "toSend"));

        PowerMock.verifyAll();
    }

}