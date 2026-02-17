class DummyClass_91581 {
    @Test
    public void test1() throws Exception {

        System.out.println(org.apache.kylin.common.util.DateFormat.formatToTimeStr(1433833611000L));
        System.out.println(org.apache.kylin.common.util.DateFormat.formatToTimeStr(1433250517000L));
        System.out.println(org.apache.kylin.common.util.DateFormat.stringToMillis("2015-06-01 00:00:00"));
        System.out.println(org.apache.kylin.common.util.DateFormat.stringToMillis("2015-05-15 17:00:00"));
        Assert.assertEquals(1568960682251L,
                org.apache.kylin.common.util.DateFormat.stringToMillis("2019-09-20T14:24:42.251+08:00"));

        String bb = "\\x00\\x00\\x00\\x00\\x01\\x3F\\xD0\\x2D\\58\\x00\\x00\\x00\\x00\\x00\\x00\\x00\\x00\\x00\\x00\\x00";//2013/07/12 07:59:37
        String cc = "\\x00\\x00\\x00\\x00\\x01\\x41\\xBE\\x8F\\xD8\\x00\\x00\\x00\\x00\\x00\\x00\\x00";//2013/10/16 08:00:00
        String dd = "\\x00\\x00\\x00\\x00\\x01\\x41\\xBE\\x8F\\xD8\\x07\\x00\\x18\\x00\\x00\\x00";

        byte[] bytes = BytesUtil.fromReadableText(dd);
        long ttt = BytesUtil.readLong(bytes, 2, 8);
        System.out.println(time(ttt));

        System.out.println("\\");
        System.out.println("n");

        System.out.println("The start key is set to " + null);
        long current = System.currentTimeMillis();
        System.out.println(time(current));

        Calendar a = Calendar.getInstance(TimeZone.getDefault(), Locale.ROOT);
        Calendar b = Calendar.getInstance(TimeZone.getDefault(), Locale.ROOT);
        Calendar c = Calendar.getInstance(TimeZone.getDefault(), Locale.ROOT);
        b.clear();
        c.clear();

        System.out.println(time(b.getTimeInMillis()));
        System.out.println(time(c.getTimeInMillis()));

        a.setTimeInMillis(current);
        b.set(a.get(Calendar.YEAR), a.get(Calendar.MONTH), a.get(Calendar.DAY_OF_MONTH), a.get(Calendar.HOUR_OF_DAY),
                a.get(Calendar.MINUTE));
        c.set(a.get(Calendar.YEAR), a.get(Calendar.MONTH), a.get(Calendar.DAY_OF_MONTH), a.get(Calendar.HOUR_OF_DAY),
                0);

        System.out.println(time(b.getTimeInMillis()));
        System.out.println(time(c.getTimeInMillis()));

    }

}