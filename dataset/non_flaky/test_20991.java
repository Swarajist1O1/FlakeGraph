class DummyClass_20991 {
    @Test
    public void testContents() {
        Meta one = new Meta("sys.cpu.user", "tag1", "value1");
        Meta two = new Meta("sys.cpu.user", "tag2", "value2");
        Meta three = new Meta("sys.cpu.user", "tag3", "value3");
        MetaKeySet mks = new MetaKeySet();
        mks.addAll(one.toKeys());
        mks.addAll(two.toKeys());
        mks.addAll(three.toKeys());
        Assert.assertEquals(7, mks.size());
        Assert.assertTrue(mks.contains(new Key("m:sys.cpu.user")));
        Assert.assertTrue(mks.contains(new Key("t:sys.cpu.user", "tag1")));
        Assert.assertTrue(mks.contains(new Key("t:sys.cpu.user", "tag2")));
        Assert.assertTrue(mks.contains(new Key("t:sys.cpu.user", "tag3")));
        Assert.assertTrue(mks.contains(new Key("v:sys.cpu.user", "tag1", "value1")));
        Assert.assertTrue(mks.contains(new Key("v:sys.cpu.user", "tag2", "value2")));
        Assert.assertTrue(mks.contains(new Key("v:sys.cpu.user", "tag3", "value3")));
    }

}