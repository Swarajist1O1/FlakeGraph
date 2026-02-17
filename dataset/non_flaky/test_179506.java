class DummyClass_179506 {
    @Test
    public void testCamelhump() {
        for (String field : fields) {
            System.out.println(field + " - " + StringUtil.convertByStyle(field, Style.camelhump));
        }
    }

}