class DummyClass_20928 {
    @Test
    public void testParseTagsWithCommas() {

        try {
            String s = "tag1=value1,tag2=3.4.3_(default\\,_Date\\,_Time)_";
            new TagListParser().parse(s);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

}