class DummyClass_21240 {
    @Test
    public void stringSet_persistAndRetrieve_returnsPersistedValue() {
        final Set<String> expected = TEST_STR_SET;

        mPreference.putStringSet(expected);
        Set<String> result = mPreference.getStringSet(TEST_DEFAULT_STR_SET);

        assertThat(result, containsInAnyOrder(expected.toArray()));
    }

}