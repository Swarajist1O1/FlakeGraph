class DummyClass_97728 {
    @Test
    public void testJackson1OptionalAnnotation() {
        Settings settings = new Settings();
        settings.optionalAnnotations.add(Nullable.class);
        ModelParser parser = new Jackson1Parser(settings, new DefaultTypeProcessor());
        testModel(parser.parseModel(Jackson1Bean.class), true);
    }

}