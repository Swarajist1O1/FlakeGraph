class DummyClass_91373 {
    @Test
    public void testCTor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        CommonIdentifiers t = new CommonIdentifiers();
        Assert.assertNotNull("exists",t);
    }

}