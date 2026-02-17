class DummyClass_148842 {
    @Test
    public void HasContentIsTrueWhenActivityAttachmentsHaveContent() {
        Activity activity = createActivity();
        ArrayList<Attachment> attachments = new ArrayList<>();
        attachments.add(CreateAttachment());

        activity.setText(null);
        activity.setSummary(null);
        activity.setAttachments(attachments);

        boolean result = activity.hasContent();

        Assert.assertEquals(result, true);
    }

}