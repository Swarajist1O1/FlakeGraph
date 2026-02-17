class DummyClass_97747 {
    @Test
    public void testGlobToRegexp() {
        Assert.assertEquals("\\Q\\E.*\\QJson\\E", Utils.globsToRegexps(Arrays.asList("**Json")).get(0).toString());
        Assert.assertEquals("\\Qcz.habarta.test.\\E[^.\\$]*\\Q\\E", Utils.globsToRegexps(Arrays.asList("cz.habarta.test.*")).get(0).toString());
    }

}