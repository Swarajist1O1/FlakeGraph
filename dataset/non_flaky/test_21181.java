class DummyClass_21181 {
    @Test
    public void testTitleComparison() throws Exception {
        mPref1.setTitle("value 1");

        assertFalse("Compare non-null to null",
                mComparisonCallback.arePreferenceContentsTheSame(mPref1, mPref2));
        assertFalse("Compare null to non-null",
                mComparisonCallback.arePreferenceContentsTheSame(mPref2, mPref1));

        mPref2.setTitle("value 1");

        assertTrue("Compare identical",
                mComparisonCallback.arePreferenceContentsTheSame(mPref1, mPref2));

        mPref2.setTitle("value 2");

        assertFalse("Compare different",
                mComparisonCallback.arePreferenceContentsTheSame(mPref1, mPref2));
    }

}