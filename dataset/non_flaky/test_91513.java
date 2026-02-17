class DummyClass_91513 {
    @Test
    public void testPrepareStatementWithMockKylinClient() throws SQLException, IOException {
        String sql = "select 1 as val";
        // mock client
        when(client.executeQuery(anyString(), Mockito.<List<Object>>any(), Mockito.<Map<String, String>>any())).thenReturn(getMockResult());

        try (KylinConnection conn = getConnectionWithMockClient()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                verify(client).executeQuery(eq(sql), Mockito.<List<Object>>any(), Mockito.<Map<String, String>>any());

                assertTrue(resultSet.next());
                ResultSetMetaData metaData = resultSet.getMetaData();
                assertEquals("VAL", metaData.getColumnName(1));
                assertEquals(1, resultSet.getInt("VAL"));
            }
        }
    }

}