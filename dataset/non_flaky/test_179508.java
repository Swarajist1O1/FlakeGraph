class DummyClass_179508 {
    @Test
    public void testCamelhumpLowercase() {
        for (String field : fields) {
            System.out.println(field + " - " + StringUtil.convertByStyle(field, Style.camelhumpAndLowercase));
        }
    }

}