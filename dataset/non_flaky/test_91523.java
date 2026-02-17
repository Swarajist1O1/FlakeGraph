class DummyClass_91523 {
    @Test
    public void testArrayValue() throws Exception {
        userNeedColNames = new String[] { "userMentionEntities", "mediaEntities" };
        List<TblColRef> allCol = mockupTblColRefList();
        TimedJsonStreamParser parser = new TimedJsonStreamParser(allCol, null);
        Object msg = mapper.readValue(new File(jsonFilePath), mapType);
        HashMap<String, Object> map = (HashMap<String, Object>) msg;
        Object array = map.get("mediaEntities");
        ByteBuffer buffer = getJsonByteBuffer(msg);
        List<StreamingMessageRow> msgList = parser.parse(buffer);
        List<String> result = msgList.get(0).getData();
        System.out.println(result);

    }

}