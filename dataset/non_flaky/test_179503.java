class DummyClass_179503 {
    @Test
    public void testNormal() {
        for (String field : fields) {
            Assert.assertEquals(field, StringUtil.convertByStyle(field, Style.normal));
        }
    }

}