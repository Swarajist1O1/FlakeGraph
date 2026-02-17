class DummyClass_91393 {
    @Test
    public void testCTor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        JdomCdiReader t = new JdomCdiReader();
        Assert.assertNotNull("exists",t);
    }

}