class DummyClass_98628 {
    @Test
    public void test_normal_debug() {
        Logs.setAdapter(new Log4jLogAdapter());
        Log log4nut = Logs.getLog(Dao.class);
        assertTrue(log4nut.getClass().getName().contains(Log4jLogAdapter.class.getName()));
        Logger log4j = LogManager.getLogger(Dao.class);

        assertEquals(log4nut.isInfoEnabled(), log4j.isInfoEnabled());
        assertEquals(log4nut.isDebugEnabled(), log4j.isDebugEnabled());
        assertEquals(log4nut.isTraceEnabled(), log4j.isTraceEnabled());
    }

}