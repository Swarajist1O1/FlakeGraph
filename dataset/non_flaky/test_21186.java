class DummyClass_21186 {
    @Test
    public void testTwoStateComparison() throws Exception {
        final TwoStatePreference checkbox1 =
                new CheckBoxPreference(InstrumentationRegistry.getTargetContext());
        final TwoStatePreference checkbox2 =
                new CheckBoxPreference(InstrumentationRegistry.getTargetContext());

        checkbox1.setChecked(true);
        checkbox2.setChecked(true);

        assertTrue("Compare checked",
                mComparisonCallback.arePreferenceContentsTheSame(checkbox1, checkbox2));

        checkbox2.setChecked(false);

        assertFalse("Compare checked/unchecked",
                mComparisonCallback.arePreferenceContentsTheSame(checkbox1, checkbox2));
        assertFalse("Compare unchecked/checked",
                mComparisonCallback.arePreferenceContentsTheSame(checkbox2, checkbox1));

        checkbox1.setChecked(false);

        assertTrue("Compare unchecked",
                mComparisonCallback.arePreferenceContentsTheSame(checkbox1, checkbox2));
    }

}