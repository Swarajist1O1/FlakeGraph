class DummyClass_148895 {
    @Test
    public void TestClone() {

        CardAction cardAction =  new CardAction();
        cardAction.setChannelData("channelData");
        cardAction.setDisplayText("displayTest");
        cardAction.setImage("image");
        cardAction.setImageAltText("imageAltText");
        cardAction.setText("text");
        cardAction.setTitle("title");
        cardAction.setType(ActionTypes.CALL);
        cardAction.setValue("value");

        CardAction newCardAction = CardAction.clone(cardAction);

        Assert.assertEquals(cardAction.getChannelData(), newCardAction.getChannelData());
        Assert.assertEquals(cardAction.getDisplayText(), newCardAction.getDisplayText());
        Assert.assertEquals(cardAction.getImage(), newCardAction.getImage());
        Assert.assertEquals(cardAction.getImageAltText(), newCardAction.getImageAltText());
        Assert.assertEquals(cardAction.getText(), newCardAction.getText());
        Assert.assertEquals(cardAction.getTitle(), newCardAction.getTitle());
        Assert.assertEquals(cardAction.getType(), newCardAction.getType());
        Assert.assertEquals(cardAction.getValue(), newCardAction.getValue());
    }

}