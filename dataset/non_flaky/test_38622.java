class DummyClass_38622 {
    @Test
    public void TestUnknownAction() throws Exception {
        Record<GenericRecord> recordRecord = mock(Record.class);
        when(recordRecord.getProperties()).thenReturn(ImmutableMap.of("ACTION", "UNKNOWN"));
        CompletableFuture<Void> future = new CompletableFuture<>();
        doAnswer(a -> future.complete(null)).when(recordRecord).fail();
        jdbcSink.write(recordRecord);
        future.get(1, TimeUnit.SECONDS);
    }

}