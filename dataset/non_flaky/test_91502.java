class DummyClass_91502 {
    @Test
    public void testSSLFromURL() throws SQLException {
        Driver driver = new DummyDriver();
        Connection conn = driver.connect("jdbc:kylin:ssl=True;//test_url/test_db", null);
        assertEquals("test_url", ((KylinConnection) conn).getBaseUrl());
        assertEquals("test_db", ((KylinConnection) conn).getProject());
        assertTrue(Boolean.parseBoolean((String) ((KylinConnection) conn).getConnectionProperties().get("ssl")));
        conn.close();
    }

}