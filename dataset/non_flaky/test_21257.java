class DummyClass_21257 {
    @Test
    public void boolean_retrieveWhenEmpty_returnsDefault() {
        final boolean expected = true;

        boolean result = mPreference.getBoolean(expected);

        assertEquals(expected, result);
    }

}