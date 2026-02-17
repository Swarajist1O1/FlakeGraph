class DummyClass_148885 {
    @Test
    public void TestGetAttachments(){
        List<MessageActionsPayloadAttachment> attachments = new ArrayList<MessageActionsPayloadAttachment>();
        MessageActionsPayload messageActionsPayload = new MessageActionsPayload();
        messageActionsPayload.setAttachments(attachments);
        List<MessageActionsPayloadAttachment> result = messageActionsPayload.getAttachments();

        Assert.assertEquals(result, attachments);
    }

}