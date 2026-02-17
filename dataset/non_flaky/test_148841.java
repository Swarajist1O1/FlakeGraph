class DummyClass_148841 {
    @Test
    public void HasContentIsTrueWhenActivitySummaryContent() {
        Activity activity = createActivity();

        activity.setText(null);
        activity.setSummary("test-summary");

        boolean result = activity.hasContent();

        Assert.assertEquals(result, true);
    }

}