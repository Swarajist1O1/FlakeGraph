class DummyClass_135063 {
    @Test
    public void testDecodingURLMustNotMutateSpaceSymbolsCaseIfSpaceDecodingDisabled() throws Exception {
        final String url = "http://localhost:3001/wild" + spaceCode + "west";

        final String result = URLUtils.decode(url, Charset.defaultCharset().name(), false, new StringBuilder());
        assertEquals(url, result);
    }

}