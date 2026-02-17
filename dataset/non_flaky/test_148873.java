class DummyClass_148873 {
    @Test
    public void TestGetMessageType(){
        String messageType = "testMessageType";
        MessageActionsPayload messageActionsPayload = new MessageActionsPayload();
        messageActionsPayload.setMessageType(messageType);
        String result = messageActionsPayload.getMessageType();

        Assert.assertEquals(result, messageType);
    }

}