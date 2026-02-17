class DummyClass_97730 {
    @Test
    public void testJackson2OptionalAnnotation() {
        Settings settings = new Settings();
        settings.optionalAnnotations.add(Nullable.class);
        ModelParser parser = new Jackson2Parser(settings, new DefaultTypeProcessor());
        testModel(parser.parseModel(Jackson2Bean.class), true);
    }

}