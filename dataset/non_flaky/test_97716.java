class DummyClass_97716 {
    @Test
    public void testJava8DateWithJackson2CustomSerialization() {
        final Settings settings = TestUtils.settings();
        settings.customTypeMappings = Collections.singletonMap("java.time.LocalDate", "[number, number, number]");
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(Java8Jackson2Dates.class));
        Assert.assertTrue(output.contains("date: [number, number, number];"));
    }

}