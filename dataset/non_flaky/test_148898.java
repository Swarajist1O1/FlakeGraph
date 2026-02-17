class DummyClass_148898 {
    @Test
    public void TestConstructorThreeParams() {
        CardAction cardAction =  new CardAction(ActionTypes.CALL, "title", "value");
        Assert.assertEquals(cardAction.getType(), ActionTypes.CALL);
        Assert.assertEquals(cardAction.getTitle(), "title");
        Assert.assertEquals(cardAction.getValue(), "value");
    }

}