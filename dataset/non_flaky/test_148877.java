class DummyClass_148877 {
    @Test
    public void TestGetSubject(){
        String subject = "testSubject";
        MessageActionsPayload messageActionsPayload = new MessageActionsPayload();
        messageActionsPayload.setSubject(subject);
        String result = messageActionsPayload.getSubject();

        Assert.assertEquals(result, subject);
    }

}