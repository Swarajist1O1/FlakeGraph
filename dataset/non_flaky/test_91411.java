class DummyClass_91411 {
    @Test
    public void testCTor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        GridLayout2 t = new GridLayout2();
        Assert.assertNotNull("exists",t);
    }

}