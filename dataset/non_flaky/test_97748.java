class DummyClass_97748 {
    @Test
    public void testPathJoin() {
        Assert.assertEquals("controller", Utils.joinPath("/controller", null));
        Assert.assertEquals("controller/", Utils.joinPath("/controller/", null));
        Assert.assertEquals("path", Utils.joinPath(null, "/path"));
        Assert.assertEquals("path/", Utils.joinPath(null, "/path/"));
        Assert.assertEquals("", Utils.joinPath(null, "/"));
        Assert.assertEquals("", Utils.joinPath("/", null));
        Assert.assertEquals("path", Utils.joinPath("/", "path"));

        Assert.assertEquals("controller", Utils.joinPath("/controller", ""));
        Assert.assertEquals("controller/", Utils.joinPath("/controller", "/"));
        Assert.assertEquals("controller/path", Utils.joinPath("/controller", "/path"));
        Assert.assertEquals("controller/path", Utils.joinPath("/controller", "path"));
        Assert.assertEquals("controller/path/", Utils.joinPath("/controller", "/path/"));

        Assert.assertEquals("controller/", Utils.joinPath("/controller/", ""));
        Assert.assertEquals("controller/", Utils.joinPath("/controller/", "/"));
        Assert.assertEquals("controller/path", Utils.joinPath("/controller/", "/path"));
        Assert.assertEquals("controller/path", Utils.joinPath("/controller/", "path"));
        Assert.assertEquals("controller/path/", Utils.joinPath("/controller/", "/path/"));
    }

}