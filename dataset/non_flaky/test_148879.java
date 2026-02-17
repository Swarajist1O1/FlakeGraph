class DummyClass_148879 {
    @Test
    public void TestGetImportance(){
        String importance = "normal";
        MessageActionsPayload messageActionsPayload = new MessageActionsPayload();
        messageActionsPayload.setImportance(importance);
        String result = messageActionsPayload.getImportance();

        Assert.assertEquals(result, importance);
    }

}