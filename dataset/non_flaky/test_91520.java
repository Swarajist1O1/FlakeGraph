class DummyClass_91520 {
    @Test
    public void testNormalValue() throws Exception {
        userNeedColNames = new String[] { "createdAt", "id", "isTruncated", "text" };
        List<TblColRef> allCol = mockupTblColRefList();
        TimedJsonStreamParser parser = new TimedJsonStreamParser(allCol, null);
        Object msg = mapper.readValue(new File(jsonFilePath), mapType);
        ByteBuffer buffer = getJsonByteBuffer(msg);
        List<StreamingMessageRow> msgList = parser.parse(buffer);
        List<String> result = msgList.get(0).getData();
        assertEquals("Jul 20, 2016 9:59:17 AM", result.get(0));
        assertEquals("755703618762862600", result.get(1));
        assertEquals("false", result.get(2));
        assertEquals("dejamos", result.get(3));
    }

}