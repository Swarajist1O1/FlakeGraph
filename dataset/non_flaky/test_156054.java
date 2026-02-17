class DummyClass_156054 {
    @Test
    public void testSafeJsonToBoolean() {
        Assert.assertNull(JsonUtils.safeJsonToBoolean(null));
        Assert.assertFalse(JsonUtils.safeJsonToBoolean("abcd"));
        Assert.assertFalse(JsonUtils.safeJsonToBoolean(""));
        Assert.assertFalse(JsonUtils.safeJsonToBoolean("3522"));
        Assert.assertFalse(JsonUtils.safeJsonToBoolean("25275738927589278572891"));
        Assert.assertFalse(JsonUtils.safeJsonToBoolean("-25275738927589278572891"));
        Assert.assertTrue(JsonUtils.safeJsonToBoolean("true"));
        Assert.assertFalse(JsonUtils.safeJsonToBoolean("false"));
        Assert.assertFalse(JsonUtils.safeJsonToBoolean("0"));
        Assert.assertFalse(JsonUtils.safeJsonToBoolean("1"));
        Assert.assertFalse(JsonUtils.safeJsonToBoolean("yes"));
        Assert.assertFalse(JsonUtils.safeJsonToBoolean("no"));
    }

}