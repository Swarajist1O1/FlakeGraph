class DummyClass_91392 {
    @Test
    public void testCTor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        DemoReadWriteAccess t = new DemoReadWriteAccess();
        Assert.assertNotNull("exists",t);
    }

}