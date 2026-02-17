class DummyClass_21183 {
    @Test
    public void testIconComparison() throws Exception {
        final Drawable drawable1 = new ComparisonDrawable(1);
        final Drawable drawable1a = new ComparisonDrawable(1);
        final Drawable drawable2 = new ComparisonDrawable(2);

        mPref1.setIcon(drawable1);

        assertFalse("Compare non-null to null",
                mComparisonCallback.arePreferenceContentsTheSame(mPref1, mPref2));
        assertFalse("Compare null to non-null",
                mComparisonCallback.arePreferenceContentsTheSame(mPref2, mPref1));

        mPref2.setIcon(drawable1);

        assertTrue("Compare aliased",
                mComparisonCallback.arePreferenceContentsTheSame(mPref1, mPref2));

        mPref2.setIcon(drawable1a);

        assertTrue("Compare equal",
                mComparisonCallback.arePreferenceContentsTheSame(mPref1, mPref2));

        mPref2.setIcon(drawable2);

        assertFalse("Compare unequal",
                mComparisonCallback.arePreferenceContentsTheSame(mPref1, mPref2));
    }

}