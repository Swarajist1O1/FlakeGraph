class DummyClass_20961 {
    @Test
    public void testRegex1() throws Exception {
        String tags = "tag1=value1,tag2=value2,tag3=value3";
        StringBuffer pattern = new StringBuffer();
        pattern.append("(^|.*,)");
        pattern.append("tag2");
        pattern.append("=");
        pattern.append("value2");
        pattern.append("(,.*|$)");
        Pattern p = Pattern.compile(pattern.toString());
        assertTrue(p.matcher(tags).matches());
    }

}