class DummyClass_70844 {
    @Test(expected = ConnectException.class)
    public void testSendRecordsProducerCallbackFail() throws Exception {
        createWorkerTask();

        SourceRecord record1 = new SourceRecord(PARTITION, OFFSET, "topic", 1, KEY_SCHEMA, KEY, RECORD_SCHEMA, RECORD);
        SourceRecord record2 = new SourceRecord(PARTITION, OFFSET, "topic", 2, KEY_SCHEMA, KEY, RECORD_SCHEMA, RECORD);

        expectSendRecordProducerCallbackFail();

        PowerMock.replayAll();

        Whitebox.setInternalState(workerTask, "toSend", Arrays.asList(record1, record2));
        Whitebox.invokeMethod(workerTask, "sendRecords");
    }

}