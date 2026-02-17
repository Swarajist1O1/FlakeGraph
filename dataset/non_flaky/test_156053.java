class DummyClass_156053 {
    @Test
    public void testSafeJsonToInteger() {
        Assert.assertNull(JsonUtils.safeJsonToInteger(null));
        Assert.assertNull(JsonUtils.safeJsonToInteger("abcd"));
        Assert.assertNull(JsonUtils.safeJsonToInteger(""));
        Assert.assertEquals((Integer) 355, (Integer) JsonUtils.safeJsonToInteger("355"));
        Assert.assertNull(JsonUtils.safeJsonToInteger("25275738927589278572891"));
        Assert.assertNull(JsonUtils.safeJsonToInteger("-25275738927589278572891"));
        Assert.assertEquals((Integer) 0, JsonUtils.safeJsonToInteger("0"));
    }

}