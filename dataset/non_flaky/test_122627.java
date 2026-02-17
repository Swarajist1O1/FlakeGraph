class DummyClass_122627 {
    @Test
    public void generic_yum_methods() {
        assertYumMethod(yum -> yum.expectInstall(packages).withEnableRepo(repos),
                yum -> yum.install(List.of(packages)).enableRepo(repos).converge(context));

        assertYumMethod(yum -> yum.expectUpdate(packages).withEnableRepo(repos),
                yum -> yum.upgrade(List.of(packages)).enableRepo(repos).converge(context));

        assertYumMethod(yum -> yum.expectRemove(packages).withEnableRepo(repos),
                yum -> yum.remove(List.of(packages)).enableRepo(repos).converge(context));

        assertYumMethod(yum -> yum.expectInstallFixedVersion(minimalPackage.toName()).withEnableRepo(repos),
                yum -> yum.installFixedVersion(minimalPackage).enableRepo(repos).converge(context));
    }

}