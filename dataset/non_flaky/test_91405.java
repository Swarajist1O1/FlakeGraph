class DummyClass_91405 {
    @Test
    public void testCTor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        Hub t = new Hub();
        Assert.assertNotNull("exists",t);
    }

}