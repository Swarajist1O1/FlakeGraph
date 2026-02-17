class DummyClass_21236 {
    @Test
    public void string_persistTwiceAndRetrieve_returnsSecondValue() {
        final String expected = "Second";

        mPreference.putString("First");
        mPreference.putString(expected);
        String result = mPreference.getString("Default");

        assertEquals(expected, result);
    }

}