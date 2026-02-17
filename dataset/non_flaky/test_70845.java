class DummyClass_70845 {
    @Test
    public void testSendRecordsTaskCommitRecordFail() throws Exception {
        createWorkerTask();

        // Differentiate only by Kafka partition so we can reuse conversion expectations
        SourceRecord record1 = new SourceRecord(PARTITION, OFFSET, "topic", 1, KEY_SCHEMA, KEY, RECORD_SCHEMA, RECORD);
        SourceRecord record2 = new SourceRecord(PARTITION, OFFSET, "topic", 2, KEY_SCHEMA, KEY, RECORD_SCHEMA, RECORD);
        SourceRecord record3 = new SourceRecord(PARTITION, OFFSET, "topic", 3, KEY_SCHEMA, KEY, RECORD_SCHEMA, RECORD);

        // Source task commit record failure will not cause the task to abort
        expectSendRecordOnce(false);
        expectSendRecordTaskCommitRecordFail(false, false);
        expectSendRecordOnce(false);

        PowerMock.replayAll();

        Whitebox.setInternalState(workerTask, "toSend", Arrays.asList(record1, record2, record3));
        Whitebox.invokeMethod(workerTask, "sendRecords");
        assertEquals(false, Whitebox.getInternalState(workerTask, "lastSendFailed"));
        assertNull(Whitebox.getInternalState(workerTask, "toSend"));

        PowerMock.verifyAll();
    }

}