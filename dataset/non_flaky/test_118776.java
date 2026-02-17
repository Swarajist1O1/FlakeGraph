class DummyClass_118776 {
    @Test
    public void testUtf8BytesWithNonSurrogatesNonAscii() {
        final char nonAscii = (char) 0x81;
        final String s = "a" + nonAscii + "b";
        checkUtf8Bytes(s);
    }

}