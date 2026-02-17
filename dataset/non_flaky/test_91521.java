class DummyClass_91521 {
    @Test
    public void testEmbeddedValue() throws Exception {
        userNeedColNames = new String[] { "user_id", "user_description", "user_isProtected",
                "user_is_Default_Profile_Image" };
        userNeedColNamesComment = new String[] { "", "", "",
                "user" + TimedJsonStreamParser.EMBEDDED_PROPERTY_SEPARATOR + "is_Default_Profile_Image" };
        List<TblColRef> allCol = mockupTblColRefListWithComment(userNeedColNamesComment);
        TimedJsonStreamParser parser = new TimedJsonStreamParser(allCol, null);
        Object msg = mapper.readValue(new File(jsonFilePath), mapType);
        ByteBuffer buffer = getJsonByteBuffer(msg);
        List<StreamingMessageRow> msgList = parser.parse(buffer);
        List<String> result = msgList.get(0).getData();
        assertEquals("4853763947", result.get(0));
        assertEquals("Noticias", result.get(1));
        assertEquals("false", result.get(2));
        assertEquals("false", result.get(3));
    }

}