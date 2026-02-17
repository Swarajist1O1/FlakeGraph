class DummyClass_91394 {
    @Test
    public void testCTor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        XmlHelper t = new XmlHelper();
        Assert.assertNotNull("exists",t);
    }

}