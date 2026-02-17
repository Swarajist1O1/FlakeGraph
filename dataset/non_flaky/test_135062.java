class DummyClass_135062 {
    @Test
    public void testDecodingWithEncodedAndDecodedSlashAndSlashDecodingDisabled() throws Exception {
        String url = "http://localhost:3001/by-path/wild%20card/wild%28west%29/wild" + spaceCode + "wolf";

        final String result = URLUtils.decode(url, Charset.defaultCharset().name(), false, new StringBuilder());
        assertEquals("http://localhost:3001/by-path/wild card/wild(west)/wild" + spaceCode + "wolf", result);
    }

}