class DummyClass_113983 {
    @Test
    public void testGetAllAsStringsMap() throws Exception {
        ConstantConfig constantConfig = new ConstantConfig();

        boolean expectedDevMode = true;
        constantConfig.setDevMode(expectedDevMode);

        String expectedActionExtensions = ".action,.some,.another";
        constantConfig.setActionExtension(Arrays.asList(expectedActionExtensions.split(",")));

        String expectedLanguage = "fr";
        constantConfig.setLocale(new Locale(expectedLanguage));

        Map<String, String> map = constantConfig.getAllAsStringsMap();

        Assert.assertEquals(String.valueOf(expectedDevMode), map.get(StrutsConstants.STRUTS_DEVMODE));
        Assert.assertEquals(expectedActionExtensions, map.get(StrutsConstants.STRUTS_ACTION_EXTENSION));
        Assert.assertEquals(null, map.get(StrutsConstants.STRUTS_I18N_RELOAD));
        Assert.assertEquals(expectedLanguage, map.get(StrutsConstants.STRUTS_LOCALE));
    }

}