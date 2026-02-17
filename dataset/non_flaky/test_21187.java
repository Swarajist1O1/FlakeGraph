class DummyClass_21187 {
    @Test
    public void testDropDownComparison() throws Exception {
        final Preference dropdown1 =
                new DropDownPreference(InstrumentationRegistry.getTargetContext());
        final Preference dropdown2 =
                new DropDownPreference(InstrumentationRegistry.getTargetContext());

        assertTrue("Compare aliased drop down pref",
                mComparisonCallback.arePreferenceContentsTheSame(dropdown1, dropdown1));
        assertFalse("Compare distinct drop down prefs",
                mComparisonCallback.arePreferenceContentsTheSame(dropdown1, dropdown2));
    }

}