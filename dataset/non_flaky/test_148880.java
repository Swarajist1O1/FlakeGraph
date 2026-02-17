class DummyClass_148880 {
    @Test
    public void TestGetLinkToMessage(){
        String linkToMessage = "https://teams.microsoft.com/l/message/testing-id";
        MessageActionsPayload messageActionsPayload = new MessageActionsPayload();
        messageActionsPayload.setLinkToMessage(linkToMessage);
        String result = messageActionsPayload.getLinkToMessage();

        Assert.assertEquals(result, linkToMessage);
    }

}