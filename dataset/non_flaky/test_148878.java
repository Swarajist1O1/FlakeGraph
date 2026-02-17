class DummyClass_148878 {
    @Test
    public void TestGetSummary(){
        String summary = "testSummary";
        MessageActionsPayload messageActionsPayload = new MessageActionsPayload();
        messageActionsPayload.setSummary(summary);
        String result = messageActionsPayload.getSummary();

        Assert.assertEquals(result, summary);
    }

}