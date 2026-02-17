class DummyClass_148884 {
    @Test
    public void TestGetAttachmentLayout(){
        String attachmentLayout = "testAttachmentLayout";
        MessageActionsPayload messageActionsPayload = new MessageActionsPayload();
        messageActionsPayload.setAttachmentLayout(attachmentLayout);
        String result = messageActionsPayload.getAttachmentLayout();

        Assert.assertEquals(result, attachmentLayout);
    }

}