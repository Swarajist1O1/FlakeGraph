class DummyClass_33664 {
    @Test
    public void testParse() {
        logger.info("parsing json string:" + jsonString);
        TestBean testBean = (TestBean) JSON.parse(jsonString);
        assert testBean.getData() != null;
        assert "tester".equals(testBean.getName());
        assert "value".equals(testBean.getData().getString("key"));
    }

}