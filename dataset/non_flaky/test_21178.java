class DummyClass_21178 {
    @Test
    public void testNull() throws Exception {
        assertTrue("Compare all null",
                mComparisonCallback.arePreferenceContentsTheSame(mPref1, mPref2));
    }

}