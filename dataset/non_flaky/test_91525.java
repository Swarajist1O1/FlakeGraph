class DummyClass_91525 {
    @Test
    public void testNullKey() throws Exception {
        userNeedColNames = new String[] { "null", "" };
        List<TblColRef> allCol = mockupTblColRefList();
        TimedJsonStreamParser parser = new TimedJsonStreamParser(allCol, null);
        Object msg = mapper.readValue(new File(jsonFilePath), mapType);
        ByteBuffer buffer = getJsonByteBuffer(msg);
        List<StreamingMessageRow> msgList = parser.parse(buffer);
        List<String> result = msgList.get(0).getData();
        assertEquals(StringUtils.EMPTY, result.get(0));
        assertEquals(StringUtils.EMPTY, result.get(1));
    }

}