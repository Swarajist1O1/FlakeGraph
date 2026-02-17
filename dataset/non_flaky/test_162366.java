class DummyClass_162366 {
    @Test
    public void testMetaInf() throws Exception {
        assertThatFileList(root.resolve("META-INF")).containsOnly(
                "MANIFEST.MF",
                "services",
                "native"
        );

        assertThatFileList(root.resolve("META-INF").resolve("native")).containsOnly(
                "liborg-testcontainers-shaded-netty-transport-native-epoll.so",
                "liborg-testcontainers-shaded-netty-transport-native-kqueue.jnilib"
        );
    }

}