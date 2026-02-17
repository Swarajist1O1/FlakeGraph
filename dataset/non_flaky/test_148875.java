class DummyClass_148875 {
    @Test
    public void TestGetLastModifiedDateTime(){
        String lastModifiedDateTime = "2000-01-01";
        MessageActionsPayload messageActionsPayload = new MessageActionsPayload();
        messageActionsPayload.setLastModifiedDateTime(lastModifiedDateTime);
        String result = messageActionsPayload.getLastModifiedDateTime();

        Assert.assertEquals(result, lastModifiedDateTime);
    }

}