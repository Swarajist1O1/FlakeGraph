class DummyClass_91376 {
    @Test
    public void testCTor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        VersionOutOfDateException t = new VersionOutOfDateException();
        Assert.assertNotNull("exists",t);
    }

}