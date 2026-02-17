class DummyClass_91390 {
    @Test
    public void testCTor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        Util t = new Util();
        Assert.assertNotNull("exists",t);
    }

}