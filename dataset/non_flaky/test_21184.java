class DummyClass_21184 {
    @Test
    public void testEnabledComparison() throws Exception {
        mPref1.setEnabled(true);
        mPref2.setEnabled(true);

        assertTrue("Compare enabled",
                mComparisonCallback.arePreferenceContentsTheSame(mPref1, mPref2));

        mPref2.setEnabled(false);

        assertFalse("Compare enabled/disabled",
                mComparisonCallback.arePreferenceContentsTheSame(mPref1, mPref2));
        assertFalse("Compare disable/enabled",
                mComparisonCallback.arePreferenceContentsTheSame(mPref2, mPref1));

        mPref1.setEnabled(false);

        assertTrue("Compare disabled",
                mComparisonCallback.arePreferenceContentsTheSame(mPref1, mPref2));
    }

}