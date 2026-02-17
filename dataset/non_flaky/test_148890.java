class DummyClass_148890 {
    @Test
    public void EntityTests_MentionSerializationDeserializationTest() {
        Mention mentionEntity = new Mention();
        mentionEntity.setText("TESTTEST");

        Assert.assertEquals("mention", mentionEntity.getType());

        Entity deserializedEntity = new Entity().setAs(mentionEntity);
        Assert.assertEquals(deserializedEntity.getType(), mentionEntity.getType());
        Assert.assertEquals(
            deserializedEntity.getProperties().get("text").textValue(), mentionEntity.getText()
        );

        Mention mentionDeserialized = deserializedEntity.getAs(Mention.class);
        Assert.assertEquals(mentionEntity.getType(), mentionDeserialized.getType());
        Assert.assertEquals(
            deserializedEntity.getProperties().get("text").textValue(), mentionEntity.getText()
        );
    }

}