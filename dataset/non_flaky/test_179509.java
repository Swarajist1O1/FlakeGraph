class DummyClass_179509 {
    @Test
    public void test(){
        Matcher matcher = DELIMITER.matcher("normal");
        if(matcher.find()){
            Assert.assertEquals("normal", matcher.group(1));
        }

        matcher = DELIMITER.matcher("`mysql`");
        if(matcher.find()){
            Assert.assertEquals("mysql", matcher.group(1));
        }

        matcher = DELIMITER.matcher("[sqlserver]");
        if(matcher.find()){
            Assert.assertEquals("sqlserver", matcher.group(1));
        }

        matcher = DELIMITER.matcher("\"oracle\"");
        if(matcher.find()){
            Assert.assertEquals("oracle", matcher.group(1));
        }
    }

}