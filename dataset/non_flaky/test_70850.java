class DummyClass_70850 {
    @Test
    public void testHeadersWithCustomConverter() throws Exception {
        StringConverter stringConverter = new StringConverter();
        TestConverterWithHeaders testConverter = new TestConverterWithHeaders();

        createWorkerTask(TargetState.STARTED, stringConverter, testConverter, stringConverter);

        List<SourceRecord> records = new ArrayList<>();

        String stringA = "ÃrvÃ­ztÅ±rÅ tÃ¼kÃ¶rfÃºrÃ³gÃ©p";
        org.apache.kafka.connect.header.Headers headersA = new ConnectHeaders();
        String encodingA = "latin2";
        headersA.addString("encoding", encodingA);

        records.add(new SourceRecord(PARTITION, OFFSET, "topic", null, Schema.STRING_SCHEMA, "a", Schema.STRING_SCHEMA, stringA, null, headersA));

        String stringB = "Ð¢ÐµÑÑÐ¾Ð²Ð¾Ðµ ÑÐ¾Ð¾Ð±ÑÐµÐ½Ð¸Ðµ";
        org.apache.kafka.connect.header.Headers headersB = new ConnectHeaders();
        String encodingB = "koi8_r";
        headersB.addString("encoding", encodingB);

        records.add(new SourceRecord(PARTITION, OFFSET, "topic", null, Schema.STRING_SCHEMA, "b", Schema.STRING_SCHEMA, stringB, null, headersB));

        Capture<ProducerRecord<byte[], byte[]>> sentRecordA = expectSendRecord(false, false, true, true, false, null);
        Capture<ProducerRecord<byte[], byte[]>> sentRecordB = expectSendRecord(false, false, true, true, false, null);

        PowerMock.replayAll();

        Whitebox.setInternalState(workerTask, "toSend", records);
        Whitebox.invokeMethod(workerTask, "sendRecords");

        assertEquals(ByteBuffer.wrap("a".getBytes()), ByteBuffer.wrap(sentRecordA.getValue().key()));
        assertEquals(
            ByteBuffer.wrap(stringA.getBytes(encodingA)),
            ByteBuffer.wrap(sentRecordA.getValue().value())
        );
        assertEquals(encodingA, new String(sentRecordA.getValue().headers().lastHeader("encoding").value()));

        assertEquals(ByteBuffer.wrap("b".getBytes()), ByteBuffer.wrap(sentRecordB.getValue().key()));
        assertEquals(
            ByteBuffer.wrap(stringB.getBytes(encodingB)),
            ByteBuffer.wrap(sentRecordB.getValue().value())
        );
        assertEquals(encodingB, new String(sentRecordB.getValue().headers().lastHeader("encoding").value()));

        PowerMock.verifyAll();
    }

}