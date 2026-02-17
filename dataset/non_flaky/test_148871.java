class DummyClass_148871 {
    @Test
    public void TestGetId(){
        String id = "testId";
        MessageActionsPayload messageActionsPayload = new MessageActionsPayload();
        messageActionsPayload.setId(id);
        String result = messageActionsPayload.getId();

        Assert.assertEquals(result, id);
    }

}