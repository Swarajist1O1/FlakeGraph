class DummyClass_99741 {
    @Test (expected = IORuntimeException.class)
    public void testFutureVersion() throws Exception
    {
        FQLQueryReader reader = new FQLQueryReader();
        File dir = Files.createTempDirectory("chronicle").toFile();
        try (ChronicleQueue queue = ChronicleQueueBuilder.single(dir).build())
        {
            ExcerptAppender appender = queue.acquireAppender();
            appender.writeDocument(new BinLog.ReleaseableWriteMarshallable() {
                protected long version()
                {
                    return 999;
                }

                protected String type()
                {
                    return FullQueryLogger.SINGLE_QUERY;
                }

                public void writeMarshallablePayload(WireOut wire)
                {
                    wire.write("future-field").text("future_value");
                }

}