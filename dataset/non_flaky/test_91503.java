class DummyClass_91503 {
    @Test
    public void testCalciteProps() throws SQLException {
        Driver driver = new DummyDriver();
        Properties props = new Properties();
        props.setProperty("kylin.query.calcite.extras-props.caseSensitive", "true");
        props.setProperty("kylin.query.calcite.extras-props.unquotedCasing", "TO_LOWER");
        props.setProperty("kylin.query.calcite.extras-props.quoting", "BRACKET");
        KylinConnection conn = (KylinConnection) driver.connect("jdbc:kylin:test_url/test_db", props);
        Properties connProps = conn.getConnectionProperties();
        assertEquals("true", connProps.getProperty("kylin.query.calcite.extras-props.caseSensitive"));
        assertEquals("TO_LOWER", connProps.getProperty("kylin.query.calcite.extras-props.unquotedCasing"));
        assertEquals("BRACKET", connProps.getProperty("kylin.query.calcite.extras-props.quoting"));

        // parameters in url is prior to props parameter
        KylinConnection conn2 = (KylinConnection) driver.connect("jdbc:kylin:kylin.query.calcite.extras-props.caseSensitive=false;" +
                "kylin.query.calcite.extras-props.unquotedCasing=UNCHANGED;" +
                "kylin.query.calcite.extras-props.quoting=BACK_TICK;" +
                "test_url/test_db", props);
        Properties connProps2 = conn2.getConnectionProperties();
        assertEquals("false", connProps2.getProperty("kylin.query.calcite.extras-props.caseSensitive"));
        assertEquals("UNCHANGED", connProps2.getProperty("kylin.query.calcite.extras-props.unquotedCasing"));
        assertEquals("BACK_TICK", connProps2.getProperty("kylin.query.calcite.extras-props.quoting"));
        conn.close();
        conn2.close();
    }

}