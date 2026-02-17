class DummyClass_38230 {
    @Test
    public void testEncodeDecodeStringUTF8() throws Exception {
        String str = "THIS IS A \u1234 TEST STRING";
        byte[] bytes = TextUtils.convertStringToBytesUtf8(str);
        assertTrue(Arrays.equals(str.getBytes("UTF-8"), bytes));
        assertEquals(str, TextUtils.convertBytesToStringUtf8(bytes));
    }

}