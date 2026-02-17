class DummyClass_21185 {
    @Test
    public void testSelectableComparison() throws Exception {
        mPref1.setSelectable(true);
        mPref2.setSelectable(true);

        assertTrue("Compare selectable",
                mComparisonCallback.arePreferenceContentsTheSame(mPref1, mPref2));

        mPref2.setSelectable(false);

        assertFalse("Compare selectable/unselectable",
                mComparisonCallback.arePreferenceContentsTheSame(mPref1, mPref2));
        assertFalse("Compare unselectable/selectable",
                mComparisonCallback.arePreferenceContentsTheSame(mPref2, mPref1));

        mPref1.setSelectable(false);

        assertTrue("Compare unselectable",
                mComparisonCallback.arePreferenceContentsTheSame(mPref1, mPref2));
    }

}