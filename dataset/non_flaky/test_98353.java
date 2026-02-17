class DummyClass_98353 {
    @AfterEach
    public void tearDown() {
        super.tearDown();
        asyncDone = false;
        status = 0;
        File file = test.toFile();
        if(file.exists()){
            file.delete();
        }
    }

}