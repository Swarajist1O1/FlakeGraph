class DummyClass_76958 {
    @Test
    public void testOptionWithDefault() {
        RssConf cfg = new RssConf();
        cfg.setInteger("int-key", 11);
        cfg.setString("string-key", "abc");

        ConfigOption<String> presentStringOption = ConfigOptions
                .key("string-key")
                .stringType()
                .defaultValue("my-beautiful-default");
        ConfigOption<Integer> presentIntOption = ConfigOptions
                .key("int-key")
                .intType()
                .defaultValue(87);

        assertEquals("abc", cfg.getString(presentStringOption));
        assertEquals("abc", cfg.getValue(presentStringOption));

        assertEquals(11, cfg.getInteger(presentIntOption));
        assertEquals("11", cfg.getValue(presentIntOption));
    }

}