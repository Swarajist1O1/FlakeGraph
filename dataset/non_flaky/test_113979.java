class DummyClass_113979 {
    @Test(dataProvider = "paramValues")
    public void shouldConvertRequestValuesToStringArrays(Object input, String[] expected) {
        Parameter.Request request = new Parameter.Request(PARAM_NAME, input);

        String[] result = request.getMultipleValues();

        assertEquals(result, expected);
        assertNotSame(result, input);
    }

}