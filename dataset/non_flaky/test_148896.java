class DummyClass_148896 {
    @Test
    public void TestCloneNull() {
        CardAction newCardAction = CardAction.clone(null);
        Assert.assertNull(newCardAction);
    }

}