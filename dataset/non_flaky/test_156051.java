class DummyClass_156051 {
    @Test
    public void testSafeJsonToString() {
        Assert.assertNull(JsonUtils.safeJsonToString(null));
        Assert.assertEquals("123", JsonUtils.safeJsonToString(123));
        Assert.assertEquals("abcd", JsonUtils.safeJsonToString("abcd"));
        Assert.assertEquals("", JsonUtils.safeJsonToString(""));
    }

}