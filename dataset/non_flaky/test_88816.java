class DummyClass_88816 {
    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        ClientConfiguration target = new ClientConfiguration()
            .setAddresses("127.0.0.1:10800", "127.0.0.1:10801")
            .setTimeout(123)
            .setBinaryConfiguration(new BinaryConfiguration()
                .setClassNames(Collections.singleton("Person"))
            )
            .setSslMode(SslMode.REQUIRED)
            .setSslClientCertificateKeyStorePath("client.jks")
            .setSslClientCertificateKeyStoreType("JKS")
            .setSslClientCertificateKeyStorePassword("123456")
            .setSslTrustCertificateKeyStorePath("trust.jks")
            .setSslTrustCertificateKeyStoreType("JKS")
            .setSslTrustCertificateKeyStorePassword("123456")
            .setSslKeyAlgorithm("SunX509");

        ByteArrayOutputStream outBytes = new ByteArrayOutputStream();

        ObjectOutput out = new ObjectOutputStream(outBytes);

        out.writeObject(target);
        out.flush();

        ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(outBytes.toByteArray()));

        Object desTarget = in.readObject();

        assertTrue(Comparers.equal(target, desTarget));
    }

}