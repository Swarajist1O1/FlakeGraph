class DummyClass_91567 {
    @Test
    public void testRandomUUID() {
        Assert.assertEquals(RandomUtil.randomUUID().toString().length(), UUID.randomUUID().toString().length());
        Assert.assertNotEquals(RandomUtil.randomUUID().toString(), RandomUtil.randomUUID().toString());
    }

}