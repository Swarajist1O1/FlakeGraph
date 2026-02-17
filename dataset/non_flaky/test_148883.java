class DummyClass_148883 {
    @Test
    public void TestGetBody(){
        MessageActionsPayloadBody body = new MessageActionsPayloadBody();
        MessageActionsPayload messageActionsPayload = new MessageActionsPayload();
        messageActionsPayload.setBody(body);
        MessageActionsPayloadBody result = messageActionsPayload.getBody();

        Assert.assertEquals(result, body);
    }

}