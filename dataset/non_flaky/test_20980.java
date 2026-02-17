class DummyClass_20980 {
    @Test
    public void testToKeys() {
        Meta one = new Meta("sys.cpu.user", "tag1", "value1");
        List<Key> keys = one.toKeys();
        Assert.assertTrue(keys.contains(new Key("m:sys.cpu.user")));
        Assert.assertTrue(keys.contains(new Key("t:sys.cpu.user", "tag1")));
        Assert.assertTrue(keys.contains(new Key("v:sys.cpu.user", "tag1", "value1")));
    }

}