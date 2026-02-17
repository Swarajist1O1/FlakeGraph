class DummyClass_148843 {
    @Test
    public void HasContentIsTrueWhenActivityChannelDataHasContent() {
        Activity activity = createActivity();

        activity.setText(null);
        activity.setSummary(null);
        activity.setChannelData("test-channelData");

        boolean result = activity.hasContent();

        Assert.assertEquals(result, true);
    }

}