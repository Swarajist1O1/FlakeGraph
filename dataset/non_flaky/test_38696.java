class DummyClass_38696 {
    @Test(expectedExceptions = PulsarClientException.InvalidMessageException.class)
    public void testNullValueFailure() throws Exception {
        String index = "testnullvaluefail";
        map.put("indexName", index);
        map.put("keyIgnore", "false");
        map.put("nullValueAction", "FAIL");
        sink.open(map, mockSinkContext);
        MockRecordNullValue mockRecordNullValue = new MockRecordNullValue();
        sink.write(mockRecordNullValue);
    }

}