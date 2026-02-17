class DummyClass_135027 {
    @Test
    public void testCompareShorterFirst() {
        HttpString accept =  new HttpString(Headers.ACCEPT_STRING);
        Assert.assertEquals(accept.compareTo(Headers.ACCEPT_CHARSET), Headers.ACCEPT.compareTo(Headers.ACCEPT_CHARSET));

        HttpString acceptCharset =  new HttpString(Headers.ACCEPT_CHARSET_STRING);
        Assert.assertEquals(acceptCharset.compareTo(Headers.ACCEPT), Headers.ACCEPT_CHARSET.compareTo(Headers.ACCEPT));
    }

}