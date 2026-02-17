class DummyClass_176777 {
    @Test
    public void shouldSupportScenarioScope() {
        simulateCucumberScenarioStart();

        final ScenarioScopedClass a = instance.getInstance(ScenarioScopedClass.class);
        a.value = 10;
        assertEquals(10, a.value);

        final ScenarioScopedClass b = instance.getInstance(ScenarioScopedClass.class);
        assertEquals(10, b.value);

        simulateCucumberScenarioStop();
        simulateCucumberScenarioStart();

        final ScenarioScopedClass c = instance.getInstance(ScenarioScopedClass.class);
        assertEquals(0, c.value);

        simulateCucumberScenarioStop();
    }

}