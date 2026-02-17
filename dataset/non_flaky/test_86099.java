class DummyClass_86099 {
    @Test
    public void automaticallyAddsPersistToStreamsStorageHandler() {
        final EventStorageHandler.Config testStorageHandlerConfig = new EventStorageHandler.Config() {
            @Override
            public String type() {
                return "storage-test";
            }

}