class DummyClass_156055 {
    @Test
    public void testSafeJsonToLong() {
        Assert.assertNull(JsonUtils.safeJsonToLong(null));
        Assert.assertNull(JsonUtils.safeJsonToLong("abcd"));
        Assert.assertNull(JsonUtils.safeJsonToLong(""));
        Assert.assertEquals((Long) (long) 355, (Long) JsonUtils.safeJsonToLong("355"));
        Assert.assertNull(JsonUtils.safeJsonToLong("25275738927589278572891"));
        Assert.assertNull(JsonUtils.safeJsonToLong("-25275738927589278572891"));
        Assert.assertEquals((Long) Long.MAX_VALUE, (Long) JsonUtils.safeJsonToLong("" + Long.MAX_VALUE));
        Assert.assertEquals((Long) Long.MIN_VALUE, (Long) JsonUtils.safeJsonToLong("" + Long.MIN_VALUE));
        Assert.assertEquals((Long) (long) 0, JsonUtils.safeJsonToLong("0"));
    }

}