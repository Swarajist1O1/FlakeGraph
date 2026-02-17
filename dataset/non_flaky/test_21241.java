class DummyClass_21241 {
    @Test
    public void stringSet_persistTwiceAndRetrieve_returnsSecondValue() {
        final Set<String> expected = TEST_STR_SET2;

        mPreference.putStringSet(TEST_STR_SET);
        mPreference.putStringSet(expected);
        Set<String> result = mPreference.getStringSet(TEST_DEFAULT_STR_SET);

        assertThat(result, containsInAnyOrder(expected.toArray()));
    }

}