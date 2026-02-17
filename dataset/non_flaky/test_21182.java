class DummyClass_21182 {
    @Test
    public void testSummaryComparison() throws Exception {
        mPref1.setSummary("value 1");

        assertFalse("Compare non-null to null",
                mComparisonCallback.arePreferenceContentsTheSame(mPref1, mPref2));
        assertFalse("Compare null to non-null",
                mComparisonCallback.arePreferenceContentsTheSame(mPref2, mPref1));

        mPref2.setSummary("value 1");

        assertTrue("Compare identical",
                mComparisonCallback.arePreferenceContentsTheSame(mPref1, mPref2));

        mPref2.setSummary("value 2");

        assertFalse("Compare different",
                mComparisonCallback.arePreferenceContentsTheSame(mPref1, mPref2));
    }

}