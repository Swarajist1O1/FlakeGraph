class DummyClass_148897 {
    @Test
    public void TestConstructorTwoParams() {
        CardAction cardAction =  new CardAction(ActionTypes.CALL, "title");
        Assert.assertEquals(cardAction.getType(), ActionTypes.CALL);
        Assert.assertEquals(cardAction.getTitle(), "title");
    }

}