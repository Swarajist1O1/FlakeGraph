class DummyClass_91514 {
    @Test
    public void testPrepareStatementWithMockHttp() throws IOException, SQLException {
        String sql = "select 1 as val";
        try (KylinConnection connection = getConnectionWithMockHttp()) {

            // mock http
            HttpResponse response = TestUtil.mockHttpResponseWithFile(200, "OK", "query.json");
            when(httpClient.execute(any(HttpUriRequest.class))).thenReturn(response);

            try (ResultSet resultSet = connection.prepareStatement(sql).executeQuery()) {
                assertTrue(resultSet.next());
                ResultSetMetaData metaData = resultSet.getMetaData();
                assertEquals("VAL", metaData.getColumnName(1));
                assertEquals(1, resultSet.getInt("VAL"));
            }
        }
    }

}