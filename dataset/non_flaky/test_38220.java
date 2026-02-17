class DummyClass_38220 {
    @Test
    public void testRemoveAllWhitespace() {
        String before = "  \r\n\n\r  \t FOOOooo\r\n\n\n\r\t\r o   \n";
        String after = TextUtils.removeAllWhitespace(before);
        assertEquals("FOOOoooo", after);
    }

}