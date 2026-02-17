class DummyClass_99742 {
    @Test (expected = IORuntimeException.class)
    public void testUnknownRecord() throws Exception
    {
        FQLQueryReader reader = new FQLQueryReader();
        File dir = Files.createTempDirectory("chronicle").toFile();
        try (ChronicleQueue queue = ChronicleQueueBuilder.single(dir).build())
        {
            ExcerptAppender appender = queue.acquireAppender();
            appender.writeDocument(new BinLog.ReleaseableWriteMarshallable() {
                protected long version()
                {
                    return FullQueryLogger.CURRENT_VERSION;
                }

                protected String type()
                {
                    return "unknown-type";
                }

                public void writeMarshallablePayload(WireOut wire)
                {
                    wire.write("unknown-field").text("unknown_value");
                }

}