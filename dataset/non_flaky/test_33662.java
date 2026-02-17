class DummyClass_33662 {
    @Test
    public void testWriteLargeSpecialStr() throws UnsupportedEncodingException {

        String tmp = this.makeSpecialChars();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 200; i++) {
            builder.append(tmp);
        }
        this.doTestWrite(builder.toString());
    }

}