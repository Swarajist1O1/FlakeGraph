class DummyClass_113698 {
    @Test
    public void gradleProof() throws Exception {
        GradleProof proof = new GradleProof();
        try {
            proof.startServer();
            String result = proof.doClient("World");
            assertEquals("Hello World", result);
        } finally {
            proof.stopServer();
        }
    }

}