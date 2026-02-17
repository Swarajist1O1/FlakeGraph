class DummyClass_21180 {
    @Test
    public void testDetached() throws Exception {
        mPref1.onDetached();
        mPref1.onAttached();
        assertFalse("Compare same, detached",
                mComparisonCallback.arePreferenceContentsTheSame(mPref1, mPref1));
    }

}