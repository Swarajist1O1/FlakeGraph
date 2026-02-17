class DummyClass_162410 {
    @Test(timeout = 30_000)
    public void testEnvVar() throws IOException {
        BufferedReader br = Unreliables.retryUntilSuccess(10, TimeUnit.SECONDS, () -> {
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);

            Socket socket = new Socket(compose.getServiceHost("alpine_1", 3000), compose.getServicePort("alpine_1", 3000));
            return new BufferedReader(new InputStreamReader(socket.getInputStream()));
        });

        Unreliables.retryUntilTrue(10, TimeUnit.SECONDS, () -> {
            while (br.ready()) {
                String line = br.readLine();
                if (line.contains(DOCKER_COMPOSE_OVERRIDE_TEST_OVERRIDE_ENV)) {
                    pass("Mapped environment variable was found");
                    return true;
                }
            }
            info("Mapped environment variable was not found yet - process probably not ready");
            Uninterruptibles.sleepUninterruptibly(100, TimeUnit.MILLISECONDS);
            return false;
        });

    }

}