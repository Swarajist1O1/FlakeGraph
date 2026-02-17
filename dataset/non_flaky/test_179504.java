class DummyClass_179504 {
    @Test
    public void testUppercase() {
        for (String field : fields) {
            Assert.assertEquals(field.toUpperCase(), StringUtil.convertByStyle(field, Style.uppercase));
        }
    }

}