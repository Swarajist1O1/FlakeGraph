class DummyClass_91410 {
    @Test
    public void testCTor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        CollapsiblePanel t = new CollapsiblePanel("test",new javax.swing.JPanel());
        Assert.assertNotNull("exists",t);
    }

}