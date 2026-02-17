class DummyClass_91398 {
    @Test
    public void testCTor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        NodeIdTextField t = new NodeIdTextField();
        Assert.assertNotNull("exists",t);
    }

}