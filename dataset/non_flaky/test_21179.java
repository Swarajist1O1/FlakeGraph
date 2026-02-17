class DummyClass_21179 {
    @Test
    public void testClassComparison() throws Exception {
        final Preference checkboxPreference =
                new CheckBoxPreference(InstrumentationRegistry.getTargetContext());
        assertFalse("Compare class",
                mComparisonCallback.arePreferenceContentsTheSame(mPref1, checkboxPreference));
    }

}