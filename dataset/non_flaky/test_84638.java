class DummyClass_84638 {
    @Test
    public void testEmptyRoundTrip() throws IOException {
        MultiResponse result = new MultiResponse();
        MultiResponse decodedResult = codeDecode(result);

        assertEquals(result, decodedResult);
        assertEquals(result.hashCode(), decodedResult.hashCode());
    }

}