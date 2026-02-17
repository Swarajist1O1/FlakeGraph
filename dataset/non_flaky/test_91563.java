class DummyClass_91563 {
    @Test
    public void testNoRevision() {
        KylinVersion ver1 = new KylinVersion("2.1");
        Assert.assertEquals(2, ver1.major);
        Assert.assertEquals(1, ver1.minor);
        Assert.assertEquals(0, ver1.revision);
    }

}