class DummyClass_122549 {
    @Test
    public void testUnitExists() {
        SystemCtl systemCtl = new SystemCtl(terminal);

        terminal.expectCommand("systemctl list-unit-files foo.service 2>&1", 0,
                "UNIT FILE STATE\n" +
                        "\n" +
                        "0 unit files listed.\n");
        assertFalse(systemCtl.serviceExists(taskContext, "foo"));

        terminal.expectCommand("systemctl list-unit-files foo.service 2>&1", 0,
                "UNIT FILE           STATE  \n" +
                        "foo.service enabled\n" +
                        "\n" +
                        "1 unit files listed.\n");
        assertTrue(systemCtl.serviceExists(taskContext, "foo"));

        terminal.expectCommand("systemctl list-unit-files foo.service 2>&1", 0, "garbage");
        try {
            systemCtl.serviceExists(taskContext, "foo");
            fail();
        } catch (Exception e) {
            assertThat(e.getMessage(), containsString("garbage"));
        }
    }

}