class DummyClass_38640 {
    @Test
    public void resolveEnvVars() {
        environmentVariables.set("VARNAME1", "varvalue1");
        environmentVariables.set("VARNAME2", "varvalue2");
        String resolved = EnvVarResolverProperties
                .resolveEnvVars("padding ${VARNAME1} ${VARNAME2} padding");
        Assert.assertEquals("padding varvalue1 varvalue2 padding", resolved);
    }

}