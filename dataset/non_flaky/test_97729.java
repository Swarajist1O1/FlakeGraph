class DummyClass_97729 {
    @Test
    public void testJackson1NoAnnotation() {
        Settings settings = new Settings();
        ModelParser parser = new Jackson1Parser(settings, new DefaultTypeProcessor());
        testModel(parser.parseModel(Jackson1Bean.class), false);
    }

}