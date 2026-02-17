class DummyClass_91515 {
    @Test
    public void testJdbcClientCalcitePropsInUrl() throws Exception {
        String sql = "select 1 as val";

        // mock client
        when(client.executeQuery(anyString(), Mockito.<List<Object>>any(), Mockito.<Map<String, String>>any())).thenReturn(getMockResult());
        Map<String, String> toggles = new HashMap<>();
        Properties info = new Properties();
        info.setProperty("caseSensitive", "false");
        info.setProperty("unquotedCasing", "UNCHANGED");
        try (KylinConnection conn = getConnectionWithMockClient("jdbc:kylin:test_url/test_db", info)) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                verify(client).executeQuery(eq(sql), Mockito.<List<Object>>any(), argThat(new ArgumentMatcher<Map<String, String>>() {
                    @Override
                    public boolean matches(Map<String, String> argument) {
                        String propsStr = argument.get("JDBC_CLIENT_CALCITE_PROPS");
                        assertNotNull(propsStr);
                        Properties props = new Properties();
                        try {
                            props.load(new StringReader(propsStr));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        assertEquals("false", props.getProperty("caseSensitive"));
                        assertEquals("UNCHANGED", props.getProperty("unquotedCasing"));
                        return true;
                    }

}