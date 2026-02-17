class DummyClass_21261 {
    @Test
    public void boolean_persistTwiceAndRetrieve_returnsSecondValue() {
        final boolean expected = false;

        mPreference.putBoolean(!expected);
        mPreference.putBoolean(expected);
        boolean result = mPreference.getBoolean(!expected);

        assertEquals(expected, result);
    }

}