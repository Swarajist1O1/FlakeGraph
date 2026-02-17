class DummyClass_91582 {
    @Test
    public void test3() throws Exception {
        FastDateFormat formatter = org.apache.kylin.common.util.DateFormat.getDateFormat("MM dd, yyyy hh:mm:ss a");

        String timeStr = "07 20, 2016 09:59:17 AM";

        System.out.println(formatter.parse(timeStr).getTime());
    }

}