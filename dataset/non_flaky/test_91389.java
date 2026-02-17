class DummyClass_91389 {
    @Test
    public void testCTor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        RestoreConfig t = new RestoreConfig();
        Assert.assertNotNull("exists",t);
    }

}