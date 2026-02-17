class DummyClass_91404 {
    @Test
    public void testCTor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        DefaultPropertyListenerSupport t = new DefaultPropertyListenerSupport();
        Assert.assertNotNull("exists",t);
    }

}