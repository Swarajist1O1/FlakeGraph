class DummyClass_21223 {
    @Test
    public void testSharedPrefNotNullIfNoDS() {
        mScreen.addPreference(mPreference);

        assertNotNull(mPreference.getSharedPreferences());
    }

}