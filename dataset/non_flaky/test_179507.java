class DummyClass_179507 {
    @Test
    public void testCamelhumpUppercase() {
        for (String field : fields) {
            System.out.println(field + " - " + StringUtil.convertByStyle(field, Style.camelhumpAndUppercase));
        }
    }

}