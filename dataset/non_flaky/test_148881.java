class DummyClass_148881 {
    @Test
    public void TestGetLocale(){
        String locale = "US";
        MessageActionsPayload messageActionsPayload = new MessageActionsPayload();
        messageActionsPayload.setLocale(locale);
        String result = messageActionsPayload.getLocale();

        Assert.assertEquals(result, locale);
    }

}