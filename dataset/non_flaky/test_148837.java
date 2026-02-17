class DummyClass_148837 {
    @Test
    public void CreateReply() {
        Activity activity = createActivity();

        String text = "test-reply";
        String locale = "en-us";

        Activity reply = activity.createReply(text, locale);

        Assert.assertEquals(reply.getType(), ActivityTypes.MESSAGE);
        Assert.assertEquals(reply.getText(), text);
        Assert.assertEquals(reply.getLocale(), locale);

        activity.setFrom(null);
        activity.setRecipient(null);
        activity.setConversation(null);
        Activity reply2 = activity.createReply(text);
        Assert.assertEquals(reply2.getType(), ActivityTypes.MESSAGE);
        Assert.assertEquals(reply2.getText(), text);
        Assert.assertEquals(reply2.getLocale(), "en-uS");
        Assert.assertTrue(reply2.getFrom() != null);
        Assert.assertTrue(reply2.getRecipient() != null);
        Assert.assertTrue(reply2.getConversation() != null);
    }

}