class DummyClass_21227 {
    @Test
    public void expandablePreference_inPreferenceScreen_collapsesCorrectly() {

        mScreen.setKey("screen");
        mScreen.setInitialExpandedChildrenCount(1);

        mScreen.addPreference(mPreference1);
        mScreen.addPreference(mPreference2);
        mScreen.addPreference(mPreference3);

        PreferenceGroupAdapter preferenceGroupAdapter = new PreferenceGroupAdapter(mScreen);

        assertEquals(2, preferenceGroupAdapter.getItemCount());

        assertEquals(mPreference1, preferenceGroupAdapter.getItem(0));
        assertEquals("Advanced", preferenceGroupAdapter.getItem(1).getTitle());
        assertEquals("Preference 2, Preference 3", preferenceGroupAdapter.getItem(1).getSummary());
    }

}