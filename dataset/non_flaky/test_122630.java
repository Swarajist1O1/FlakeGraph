class DummyClass_122630 {
    @Test
    public void testAllValidFormats() {
        // name
        verifyPackageName(
                "docker-engine-selinux",
                null,
                "docker-engine-selinux",
                null,
                null,
                null,
                "docker-engine-selinux",
                null);

        // name.arch
        verifyPackageName(
                "docker-engine-selinux.x86_64",
                null,
                "docker-engine-selinux",
                null,
                null,
                "x86_64",
                "docker-engine-selinux.x86_64",
                null);

        // name-ver-rel
        verifyPackageName("docker-engine-selinux-1.12.6-1.el7",
                null,
                "docker-engine-selinux",
                "1.12.6",
                "1.el7",
                null,
                "docker-engine-selinux-1.12.6-1.el7",
                "0:docker-engine-selinux-1.12.6-1.el7.*");

        // name-ver-rel.arch
        verifyPackageName("docker-engine-selinux-1.12.6-1.el7.x86_64",
                null,
                "docker-engine-selinux",
                "1.12.6",
                "1.el7",
                "x86_64",
                "docker-engine-selinux-1.12.6-1.el7.x86_64",
                "0:docker-engine-selinux-1.12.6-1.el7.*");

        // name-epoch:ver-rel.arch
        verifyPackageName(
                "docker-2:1.12.6-71.git3e8e77d.el7.centos.1.x86_64",
                "2",
                "docker",
                "1.12.6",
                "71.git3e8e77d.el7.centos.1",
                "x86_64",
                "2:docker-1.12.6-71.git3e8e77d.el7.centos.1.x86_64",
                "2:docker-1.12.6-71.git3e8e77d.el7.centos.1.*");

        // epoch:name-ver-rel.arch
        verifyPackageName(
                "2:docker-1.12.6-71.git3e8e77d.el7.centos.1.x86_64",
                "2",
                "docker",
                "1.12.6",
                "71.git3e8e77d.el7.centos.1",
                "x86_64",
                "2:docker-1.12.6-71.git3e8e77d.el7.centos.1.x86_64",
                "2:docker-1.12.6-71.git3e8e77d.el7.centos.1.*");
    }

}