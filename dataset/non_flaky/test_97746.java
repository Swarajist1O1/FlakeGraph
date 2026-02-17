class DummyClass_97746 {
    @Test
    public void testReplaceExtension() {
        Assert.assertEquals(new File("test.dir/test.js"), Utils.replaceExtension(new File("test.dir/test"), ".js"));
        Assert.assertEquals(new File("test.dir/test.1.js"), Utils.replaceExtension(new File("test.dir/test.1.ts"), ".js"));
    }

}