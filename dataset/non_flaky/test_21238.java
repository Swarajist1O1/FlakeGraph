class DummyClass_21238 {
    @Test
    public void stringSet_persist_getsStoredToSharedPrefs() {
        boolean wasPersisted = mPreference.putStringSet(TEST_DEFAULT_STR_SET);

        assertTrue(wasPersisted);
        assertThat(mSharedPref.getStringSet(KEY, null),
                containsInAnyOrder(TEST_DEFAULT_STR_SET.toArray()));
    }

}