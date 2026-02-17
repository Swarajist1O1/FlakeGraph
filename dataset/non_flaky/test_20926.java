class DummyClass_20926 {
    @Test
    public void testListCombine() {
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag("tag1", "value1"));
        tags.add(new Tag("tag2", "value2"));
        String combined = new TagListParser().combine(tags);
        Assert.assertEquals("tag1=value1,tag2=value2", combined);
    }

}