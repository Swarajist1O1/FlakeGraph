class DummyClass_122628 {
    @Test
    public void expect_query_installed() {
        Stream.of(minimalPackage, fullPackage, null).forEach(pkg -> {
            yum.expectQueryInstalled(packages[0]).andReturn(pkg);
            assertEquals(Optional.ofNullable(pkg), yum.queryInstalled(context, packages[0]));
            terminal.verifyAllCommandsExecuted();
        });
    }

}