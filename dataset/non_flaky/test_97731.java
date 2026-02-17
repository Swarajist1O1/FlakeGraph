class DummyClass_97731 {
    @Test
    public void testJackson2NoAnnotation() {
        Settings settings = new Settings();
        ModelParser parser = new Jackson2Parser(settings, new DefaultTypeProcessor());
        testModel(parser.parseModel(Jackson2Bean.class), false);
    }

}