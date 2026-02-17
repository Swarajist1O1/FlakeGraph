class DummyClass_21246 {
    @Test
    public void int_persistTwiceAndRetrieve_returnsSecondValue() {
        final int expected = 2;

        mPreference.putInt(1);
        mPreference.putInt(expected);
        int result = mPreference.getInt(-1);

        assertEquals(expected, result);
    }

}