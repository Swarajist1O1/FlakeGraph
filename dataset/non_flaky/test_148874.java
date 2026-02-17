class DummyClass_148874 {
    @Test
    public void TestGetCreatedDateTime(){
        String createdDateTime = "2000-01-01";
        MessageActionsPayload messageActionsPayload = new MessageActionsPayload();
        messageActionsPayload.setCreatedDateTime(createdDateTime);
        String result = messageActionsPayload.getCreatedDateTime();

        Assert.assertEquals(result, createdDateTime);
    }

}