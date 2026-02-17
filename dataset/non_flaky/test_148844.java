class DummyClass_148844 {
    @Test
    public void GetMentions() {
        ArrayList<Entity> mentions = new ArrayList<Entity>();

        Entity mentionEntity = new Entity();
        mentionEntity.setType("mention");
        mentions.add(mentionEntity);
        Entity reactionEntity = new Entity();
        reactionEntity.setType("reaction");
        mentions.add(reactionEntity);

        Activity activity = createActivity();

        activity.setEntities(mentions);

        List<Mention> mentionsResult = activity.getMentions();

        Assert.assertEquals(mentionsResult.size(), 1);
        Assert.assertEquals(mentionsResult.get(0).getType(), "mention");
    }

}