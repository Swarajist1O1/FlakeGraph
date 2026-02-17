class DummyClass_162420 {
    @Test
    public void filePermissions() throws TimeoutException {

        WaitingConsumer consumer = new WaitingConsumer();

        ImageFromDockerfile image = new ImageFromDockerfile()
                .withFileFromTransferable("/someFile.txt", new Transferable() {
                    @Override
                    public long getSize() {
                        return 0;
                    }

}