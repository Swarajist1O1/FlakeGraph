class DummyClass_95667 {
    @Test
    public void testGetParameters() {
        Parameter[] parameters = strategy.getParameters();

        assertEquals(1, parameters.length);

        Parameter parameter = parameters[0];

        assertNotNull(parameter);
        assertEquals(SpringEnvironmentPropertyActivationStrategy.PARAM_NAME, parameter.getName());
        assertTrue(parameter.isOptional());
        assertTrue(Strings.isNotBlank(parameter.getLabel()));
        assertTrue(Strings.isNotBlank(parameter.getDescription()));
    }

}