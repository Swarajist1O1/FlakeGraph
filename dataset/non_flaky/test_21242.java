class DummyClass_21242 {
    @Test
    public void int_retrieveWhenEmpty_returnsDefault() {
        final int expected = 1;
        int result = mPreference.getInt(expected);

        assertEquals(expected, result);
    }

}