class DummyClass_148867 {
    @Test
    public void TestPropertySetterGetter() {
        MediaCard mediaCard = new MediaCard();
        mediaCard.setAspect("aspect");
        mediaCard.setAutoloop(true);
        mediaCard.setAutostart(true);

        List<CardAction> buttons = new ArrayList<CardAction>();
        CardAction cardAction1 = new CardAction(ActionTypes.CALL, "test1");
        CardAction cardAction2 = new CardAction(ActionTypes.DOWNLOAD_FILE, "test2");
        buttons.add(cardAction1);
        buttons.add(cardAction2);
        mediaCard.setButtons(buttons);

        mediaCard.setDuration("duration");

        ThumbnailUrl thumbnailUrl = new ThumbnailUrl();
        thumbnailUrl.setAlt("alt");
        thumbnailUrl.setUrl("testUrl");
        mediaCard.setImage(thumbnailUrl);

        mediaCard.setShareable(true);
        mediaCard.setSubtitle("subTitle");
        mediaCard.setText("text");
        mediaCard.setTitle("title");
        mediaCard.setValue("value");

        Assert.assertEquals(mediaCard.getAspect(), "aspect");
        Assert.assertEquals(mediaCard.getAutoloop(), true);
        Assert.assertEquals(mediaCard.getAutostart(), true);
        Assert.assertEquals(mediaCard.getButtons().size(), 2);
        Assert.assertEquals(mediaCard.getButtons().get(0).getType(), ActionTypes.CALL);
        Assert.assertEquals(mediaCard.getButtons().get(0).getTitle(), "test1");
        Assert.assertEquals(mediaCard.getButtons().get(1).getType(), ActionTypes.DOWNLOAD_FILE);
        Assert.assertEquals(mediaCard.getButtons().get(1).getTitle(), "test2");
        Assert.assertEquals(mediaCard.getDuration(), "duration");
        Assert.assertEquals(mediaCard.getImage().getUrl(), "testUrl");
        Assert.assertEquals(mediaCard.getImage().getAlt(), "alt");
        Assert.assertEquals(mediaCard.getShareable(), true);
        Assert.assertEquals(mediaCard.getSubtitle(), "subTitle");
        Assert.assertEquals(mediaCard.getText(), "text");
        Assert.assertEquals(mediaCard.getTitle(), "title");
        Assert.assertEquals(mediaCard.getValue(), "value");
    }

}