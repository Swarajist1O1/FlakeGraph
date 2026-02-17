class DummyClass_33661 {
    @Test
    public void testWriteLargeBasicStr() throws UnsupportedEncodingException {
        String tmp = new String(IOUtils.DIGITS);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 200; i++) {
            builder.append(tmp);
        }
        this.doTestWrite(builder.toString());
    }

}