class DummyClass_21232 {
    @Test
    public void string_retrieveWhenEmpty_returnsDefault() {
        final String expected = "Default";

        String result = mPreference.getString(expected);

        assertEquals(expected, result);
    }

}