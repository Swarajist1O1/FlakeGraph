class DummyClass_97686 {
    @Test
    public void testSplitIdentifierIntoWords() {
        Assert.assertEquals("Red", splitIdentifierIntoWords("Red"));
        Assert.assertEquals("ATYPE", splitIdentifierIntoWords("ATYPE"));
        Assert.assertEquals("camel Case Type", splitIdentifierIntoWords("camelCaseType"));
        Assert.assertEquals("Pascal Case Type", splitIdentifierIntoWords("PascalCaseType"));
        Assert.assertEquals("UPPER CASE TYPE", splitIdentifierIntoWords("UPPER_CASE_TYPE"));
        Assert.assertEquals("XML Http Request", splitIdentifierIntoWords("XMLHttpRequest"));
        Assert.assertEquals("HÃÄKY A ÄÃRKY", splitIdentifierIntoWords("HÃÄKY_A_ÄÃRKY"));
        Assert.assertEquals("HÃ¡Äky A ÄÃ¡rky", splitIdentifierIntoWords("HÃ¡ÄkyAÄÃ¡rky"));
        Assert.assertEquals("String 2 Json", splitIdentifierIntoWords("String2Json"));
        Assert.assertEquals("string 2 json", splitIdentifierIntoWords("string2json"));
        Assert.assertEquals("version 42 final", splitIdentifierIntoWords("version42final"));
    }

}