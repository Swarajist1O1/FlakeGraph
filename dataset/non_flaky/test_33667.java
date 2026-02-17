class DummyClass_33667 {
    @Test
    public void testBug1884() {
        Calendar cale = Calendar.getInstance();
        cale.clear();
        cale.setTimeZone( TimeZone.getTimeZone( "GMT+7" ) );
        cale.set( 2018, Calendar.MAY, 31, 19, 13, 42 );
        Date date = cale.getTime();

        String s1 = "{date: \"2018-05-31T19:13:42+07:00\"}"; // éè¯¯ç
        String s2 = "{date: \"2018-05-31T19:13:42.000+07:00\"}"; // æ­£ç¡®ç
        Date date1 = JSON.parseObject( s1, JSONObject.class ).getDate( "date" );
        Date date2 = JSON.parseObject( s2, JSONObject.class ).getDate( "date" );
        assertEquals(date1, date2);
        assertEquals(date, date1);
        assertEquals(date, date2);
    }

}