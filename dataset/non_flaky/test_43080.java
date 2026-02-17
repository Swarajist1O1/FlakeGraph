class DummyClass_43080 {
    @Test
    public void testUpdate()
    {
        if (!hasBehavior(SUPPORTS_UPDATE)) {
            // Note this change is a no-op, if actually run
            assertQueryFails("UPDATE nation SET nationkey = nationkey + regionkey WHERE regionkey < 1", "This connector does not support updates");
            return;
        }

        try (TestTable table = new TestTable(getQueryRunner()::execute, "test_update", "AS TABLE tpch.tiny.nation")) {
            String tableName = table.getName();
            assertUpdate("UPDATE " + tableName + " SET nationkey = 100 + nationkey WHERE regionkey = 2", 5);
            assertThat(query("SELECT * FROM " + tableName))
                    .skippingTypesCheck()
                    .matches("SELECT IF(regionkey=2, nationkey + 100, nationkey) nationkey, name, regionkey, comment FROM tpch.tiny.nation");

            // UPDATE after UPDATE
            assertUpdate("UPDATE " + tableName + " SET nationkey = nationkey * 2 WHERE regionkey IN (2,3)", 10);
            assertThat(query("SELECT * FROM " + tableName))
                    .skippingTypesCheck()
                    .matches("SELECT CASE regionkey WHEN 2 THEN 2*(nationkey+100) WHEN 3 THEN 2*nationkey ELSE nationkey END nationkey, name, regionkey, comment FROM tpch.tiny.nation");
        }
    }

}