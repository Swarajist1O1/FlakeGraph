class DummyClass_156052 {
    @Test
    public void testSafeJsonToDouble() {
        Assert.assertNull(JsonUtils.safeJsonToDouble(null));
        Assert.assertNull(JsonUtils.safeJsonToDouble("abcd"));
        Assert.assertNull(JsonUtils.safeJsonToDouble(""));
        Assert.assertEquals((Double) (double) 35141, JsonUtils.safeJsonToDouble("35141"), 0);
        Assert.assertEquals((Double) (double) 0, JsonUtils.safeJsonToDouble("0"), 0);
    }

}