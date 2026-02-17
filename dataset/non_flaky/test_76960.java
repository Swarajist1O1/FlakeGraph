class DummyClass_76960 {
    @Test
    public void testOptionWithNoDefault() {
        RssConf cfg = new RssConf();
        cfg.setInteger("int-key", 11);
        cfg.setString("string-key", "abc");

        ConfigOption<String> presentStringOption = ConfigOptions
                .key("string-key")
                .stringType()
                .noDefaultValue();

        assertEquals("abc", cfg.getString(presentStringOption));
        assertEquals("abc", cfg.getValue(presentStringOption));

        // test getting default when no value is present

        ConfigOption<String> stringOption = ConfigOptions
                .key("test")
                .stringType()
                .noDefaultValue();

        // getting strings for null should work
        assertNull(cfg.getValue(stringOption));
        assertNull(cfg.getString(stringOption));

        // overriding the null default should work
        assertEquals("override", cfg.getString(stringOption, "override"));
    }

}