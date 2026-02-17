class DummyClass_91388 {
    @Test
    public void testCTor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        BackupConfig t = new BackupConfig();
        Assert.assertNotNull("exists",t);
    }

}