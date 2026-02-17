class DummyClass_33659 {
    @Test
    public void testWriteLiteBasicStr() throws UnsupportedEncodingException {
        String targetStr = new String(IOUtils.DIGITS);
        this.doTestWrite(targetStr);
    }

}