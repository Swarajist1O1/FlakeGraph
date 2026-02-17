class DummyClass_148840 {
    @Test
    public void HasContentIsTrueWhenActivityTextHasContent() {
        Activity activity = createActivity();

        activity.setText("test-text");

        boolean result = activity.hasContent();

        Assert.assertEquals(result, true);
    }

}