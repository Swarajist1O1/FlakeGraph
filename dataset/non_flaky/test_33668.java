class DummyClass_33668 {
    @Test
    public void testBug376() {
        Calendar cale = Calendar.getInstance();
        cale.clear();
        cale.setTimeZone( TimeZone.getTimeZone( "GMT" ) );
        cale.set( 2018, Calendar.MAY, 31, 19, 13, 42 );
        Date date = cale.getTime();

        String s1 = "{date: \"2018-05-31T19:13:42Z\"}";
        String s2 = "{date: \"2018-05-31T19:13:42.000Z\"}";

        Date date1 = JSON.parseObject( s1, JSONObject.class ).getDate( "date" );
        Date date2 = JSON.parseObject( s2, JSONObject.class ).getDate( "date" );

        assertEquals( date1, date2 );
        assertEquals( date, date1 );
        assertEquals( date, date2 );
    }

}