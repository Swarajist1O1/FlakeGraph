class DummyClass_148882 {
    @Test
    public void TestGetFrom(){
        MessageActionsPayloadFrom from = new MessageActionsPayloadFrom();
        MessageActionsPayload messageActionsPayload = new MessageActionsPayload();
        messageActionsPayload.setFrom(from);
        MessageActionsPayloadFrom result = messageActionsPayload.getFrom();

        Assert.assertEquals(result, from);
    }

}