class DummyClass_20925 {
    @Test
    public void testListParse() {
        String value = "tag1=value1,tag2=value2";
        List<Tag> tags = new TagListParser().parse(value);
        Assert.assertEquals(2, tags.size());
        Assert.assertEquals(new Tag("tag1", "value1"), tags.get(0));
        Assert.assertEquals(new Tag("tag2", "value2"), tags.get(1));
    }

}