class DummyClass_98643 {
    @Test
    public void test_sql_date() {
    	resp = post("/adaptor/sqldate", "checkDate=2016-01-29");
        assertEquals(200, resp.getStatus());
        assertEquals("2016-01-29", resp.getContent());
    }

}