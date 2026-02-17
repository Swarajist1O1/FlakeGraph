class DummyClass_38219 {
    @Test
    public void testStringForValue() throws Exception {
        assertEquals("0", TextUtils.getStringForValue(0.0));
        assertEquals("5", TextUtils.getStringForValue(5.0));
        assertEquals("100", TextUtils.getStringForValue(100.0));
        assertEquals("9.2k", TextUtils.getStringForValue(9204.0));
        assertEquals("9.5k", TextUtils.getStringForValue(9499.0));
        assertEquals("10.0k", TextUtils.getStringForValue(9999.0));
        assertEquals("100k", TextUtils.getStringForValue(99999.0));
        assertEquals("100k", TextUtils.getStringForValue(100000.0));
        assertEquals("1.0M", TextUtils.getStringForValue(1000000.0));
        assertEquals("1.5M", TextUtils.getStringForValue(1499999.0));
        assertEquals("10M", TextUtils.getStringForValue(10000000.0));
        assertEquals("15M", TextUtils.getStringForValue(14999999.0));
        assertEquals("100M", TextUtils.getStringForValue(100000000.0));
        assertEquals("150M", TextUtils.getStringForValue(149999990.0));
        assertEquals("1.0B", TextUtils.getStringForValue(1000000000.0));
        assertEquals("1.5B", TextUtils.getStringForValue(1499999900.0));
        assertEquals("10B", TextUtils.getStringForValue(10000000000.0));
        assertEquals("15B", TextUtils.getStringForValue(14999999000.0));
        assertEquals("100B", TextUtils.getStringForValue(100000000000.0));
        assertEquals("150B", TextUtils.getStringForValue(149999990000.0));
        assertEquals("1.0T", TextUtils.getStringForValue(1000000000000.0));
        assertEquals("1.5T", TextUtils.getStringForValue(1499999900000.0));
        assertEquals("10T", TextUtils.getStringForValue(10000000000000.0));
        assertEquals("15T", TextUtils.getStringForValue(14999999000000.0));
        assertEquals("100T", TextUtils.getStringForValue(100000000000000.0));
        assertEquals("150T", TextUtils.getStringForValue(149999990000000.0));
        assertEquals("1.0e+15", TextUtils.getStringForValue(1000000000000000.0));
        assertEquals("1.5e+15", TextUtils.getStringForValue(1499999900000000.0));

        assertEquals("-5", TextUtils.getStringForValue(-5.0));
        assertEquals("-100", TextUtils.getStringForValue(-100.0));
        assertEquals("-9.2k", TextUtils.getStringForValue(-9204.0));
        assertEquals("-9.5k", TextUtils.getStringForValue(-9499.0));
        assertEquals("-10.0k", TextUtils.getStringForValue(-9999.0));
        assertEquals("-100k", TextUtils.getStringForValue(-99999.0));
        assertEquals("-100k", TextUtils.getStringForValue(-100000.0));
        assertEquals("-1.0M", TextUtils.getStringForValue(-1000000.0));
        assertEquals("-1.5M", TextUtils.getStringForValue(-1499999.0));
        assertEquals("-10M", TextUtils.getStringForValue(-10000000.0));
        assertEquals("-15M", TextUtils.getStringForValue(-14999999.0));
        assertEquals("-100M", TextUtils.getStringForValue(-100000000.0));
        assertEquals("-150M", TextUtils.getStringForValue(-149999990.0));
        assertEquals("-1.0B", TextUtils.getStringForValue(-1000000000.0));
        assertEquals("-1.5B", TextUtils.getStringForValue(-1499999900.0));
        assertEquals("-1.5B", TextUtils.getStringForValue(-1500000001.0));
        assertEquals("-10B", TextUtils.getStringForValue(-10000000000.0));
        assertEquals("-15B", TextUtils.getStringForValue(-14999999000.0));
        assertEquals("-100B", TextUtils.getStringForValue(-100000000000.0));
        assertEquals("-150B", TextUtils.getStringForValue(-149999990000.0));
        assertEquals("-1.0T", TextUtils.getStringForValue(-1000000000000.0));
        assertEquals("-1.5T", TextUtils.getStringForValue(-1499999900000.0));
        assertEquals("-10T", TextUtils.getStringForValue(-10000000000000.0));
        assertEquals("-15T", TextUtils.getStringForValue(-14999999000000.0));
        assertEquals("-100T", TextUtils.getStringForValue(-100000000000000.0));
        assertEquals("-150T", TextUtils.getStringForValue(-149999990000000.0));
        assertEquals("-1.0e+15", TextUtils.getStringForValue(-1000000000000000.0));
        assertEquals("-1.5e+15", TextUtils.getStringForValue(-1499999900000000.0));
    }

}