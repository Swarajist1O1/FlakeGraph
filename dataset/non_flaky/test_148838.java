class DummyClass_148838 {
    @Test
    public void CreateReplyWithoutArguments() {
        Activity activity = createActivity();

        Activity reply = activity.createReply();

        Assert.assertEquals(reply.getType(), ActivityTypes.MESSAGE);
        Assert.assertEquals(reply.getText(), "");
        Assert.assertEquals(reply.getLocale(), activity.getLocale());
    }

}