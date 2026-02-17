class DummyClass_76959 {
    @Test
    public void testSetStringAndGetConcreteType() {
        RssConf conf = new RssConf();
        conf.setString("boolean-type", "true");
        conf.setString("int-type", "1111");
        conf.setString("long-type", "1000");
        assertTrue(conf.getBoolean("boolean-type", false));
        assertEquals(conf.getInteger("int-type", 100), 1111);
        assertEquals(conf.getLong("long-type", 222L), 1000L);
    }

}