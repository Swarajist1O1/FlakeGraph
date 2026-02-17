class DummyClass_38639 {
    @Test
    public void resolveEnvVar() {
        environmentVariables.set("VARNAME", "varvalue");
        String resolved = EnvVarResolverProperties.resolveEnvVars("padding ${VARNAME} padding");
        Assert.assertEquals("padding varvalue padding", resolved);
    }

}