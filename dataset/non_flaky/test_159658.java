class DummyClass_159658 {
    @Test
    public void testEncodingUpdating2SQL() throws Exception {
        assumeNotNull(this.getDatabase());

        Liquibase liquibase = createLiquibase(encodingChangeLog);

        StringWriter writer=new StringWriter();
        liquibase.update(this.contexts,writer);
        assertTrue("Update to SQL preserves encoding",
            new RegexMatcher(writer.toString(), new String[] {
                //For the UTF-8 encoded cvs
                "^.*INSERT.*VALUES.*Ã Ã¨Ã¬Ã²Ã¹Ã¡Ã©Ã­Ã³ÃºÃÃÃÃÃÃÃÃÃÃÃ¢ÃªÃ®Ã´Ã»Ã¤Ã«Ã¯Ã¶Ã¼.*?\\)",
                "Ã§Ã±Â®",
                //For the latin1 one
                "^.*INSERT.*VALUES.*Ã Ã¨Ã¬Ã²Ã¹Ã¡Ã©Ã­Ã³ÃºÃÃÃÃÃÃÃÃÃÃÃ¢ÃªÃ®Ã´Ã»Ã¤Ã«Ã¯Ã¶Ã¼.*?\\)",
                "Ã§Ã±Â®"
            }).allMatchedInSequentialOrder());
    }

}