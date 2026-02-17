class DummyClass_148894 {
    @Test
    public void TestImplicitConversation() {
        SuggestedActions actions = new SuggestedActions(
            new CardAction[] { new CardAction("x"), new CardAction("y"), new CardAction("z") }
        );

        Assert.assertEquals("x", actions.getActions().get(0).getTitle());
        Assert.assertEquals("x", actions.getActions().get(0).getValue());
        Assert.assertEquals("y", actions.getActions().get(1).getTitle());
        Assert.assertEquals("y", actions.getActions().get(1).getValue());
        Assert.assertEquals("z", actions.getActions().get(2).getTitle());
        Assert.assertEquals("z", actions.getActions().get(2).getValue());
    }

}