class DummyClass_21237 {
    @Test
    public void stringSet_retrieveWhenEmpty_returnsDefault() {
        final Set<String> expected = TEST_DEFAULT_STR_SET;

        Set<String> result = mPreference.getStringSet(expected);

        assertThat(result, containsInAnyOrder(expected.toArray()));
    }

}