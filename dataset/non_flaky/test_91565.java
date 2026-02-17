class DummyClass_91565 {
    @Test
    public void testCompare() {
        Assert.assertEquals(true, KylinVersion.isBefore200("1.9.9"));
        Assert.assertEquals(false, KylinVersion.isBefore200("2.0.0"));
        Assert.assertEquals(true, new KylinVersion("2.1.0").compareTo(new KylinVersion("2.1.0.123")) < 0);
    }

}