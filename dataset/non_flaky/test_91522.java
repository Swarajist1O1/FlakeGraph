class DummyClass_91522 {
    @Test
    public void testEmbeddedValueFaultTolerant() throws Exception {
        userNeedColNames = new String[] { "user_id", "nonexisted_description" };
        userNeedColNamesComment = new String[] { "", "" };
        List<TblColRef> allCol = mockupTblColRefList();
        TimedJsonStreamParser parser = new TimedJsonStreamParser(allCol, null);
        Object msg = mapper.readValue(new File(jsonFilePath), mapType);
        ByteBuffer buffer = getJsonByteBuffer(msg);
        List<StreamingMessageRow> msgList = parser.parse(buffer);
        List<String> result = msgList.get(0).getData();
        assertEquals("4853763947", result.get(0));
        assertEquals(StringUtils.EMPTY, result.get(1));
    }

}