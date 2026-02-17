class DummyClass_148876 {
    @Test
    public void TestGetDeleted(){
        Boolean deleted = false;
        MessageActionsPayload messageActionsPayload = new MessageActionsPayload();
        messageActionsPayload.setDeleted(deleted);
        Boolean result = messageActionsPayload.getDeleted();

        Assert.assertEquals(result, deleted);
    }

}