class DummyClass_135028 {
    @Test
    public void testCompare() {
        HttpString contentType =  new HttpString(Headers.CONTENT_TYPE_STRING);
        Assert.assertEquals(contentType.compareTo(Headers.COOKIE), Headers.CONTENT_TYPE.compareTo(Headers.COOKIE));

        HttpString cookie =  new HttpString(Headers.COOKIE_STRING);
        Assert.assertEquals(cookie.compareTo(Headers.CONTENT_TYPE), Headers.COOKIE.compareTo(Headers.CONTENT_TYPE));
    }

}