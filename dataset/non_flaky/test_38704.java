class DummyClass_38704 {
    @Test
    public void testOpenAndRead() throws Exception {
        kafkaConnectSource = new KafkaConnectSource();
        kafkaConnectSource.open(config, context);

        // use FileStreamSourceConnector, each line is a record, need "\n" and end of each record.
        OutputStream os = Files.newOutputStream(tempFile.toPath());

        String line1 = "This is the first line\n";
        os.write(line1.getBytes());
        os.flush();
        log.info("write 2 lines.");

        String line2 = "This is the second line\n";
        os.write(line2.getBytes());
        os.flush();

        log.info("finish write, will read 2 lines");

        // Note: FileStreamSourceTask read the whole line as Value, and set Key as null.
        Record<KeyValue<byte[], byte[]>> record = kafkaConnectSource.read();
        String readBack1 = new String(record.getValue().getValue());
        assertTrue(line1.contains(readBack1));
        assertNull(record.getValue().getKey());
        log.info("read line1: {}", readBack1);
        record.ack();

        record = kafkaConnectSource.read();
        String readBack2 = new String(record.getValue().getValue());
        assertTrue(line2.contains(readBack2));
        assertNull(record.getValue().getKey());
        assertTrue(record.getPartitionId().isPresent());
        assertFalse(record.getPartitionIndex().isPresent());
        log.info("read line2: {}", readBack2);
        record.ack();

        String line3 = "This is the 3rd line\n";
        os.write(line3.getBytes());
        os.flush();

        try {
            kafkaConnectSource.read();
            fail("expected exception");
        } catch (Exception e) {
            log.info("got exception", e);
            assertTrue(e.getCause().getCause() instanceof org.apache.kafka.connect.errors.ConnectException);
        }
    }

}