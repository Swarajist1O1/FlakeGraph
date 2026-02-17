class DummyClass_91564 {
    @Test
    public void testToString() {
        KylinVersion ver1 = new KylinVersion("2.1.7.321");
        Assert.assertEquals(2, ver1.major);
        Assert.assertEquals(1, ver1.minor);
        Assert.assertEquals(7, ver1.revision);
        Assert.assertEquals(321, ver1.internal);
        Assert.assertEquals("2.1.7.321", ver1.toString());
    }

}