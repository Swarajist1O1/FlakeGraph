class DummyClass_91506 {
    @Test(expected = AssertionError.class)
    public void retrieveMetaDataWithWrongProject() throws IOException {
        client.retrieveMetaData("defualt2");
    }

}