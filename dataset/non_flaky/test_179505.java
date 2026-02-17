class DummyClass_179505 {
    @Test
    public void testLowercase() {
        for (String field : fields) {
            Assert.assertEquals(field.toLowerCase(), StringUtil.convertByStyle(field, Style.lowercase));
        }
    }

}