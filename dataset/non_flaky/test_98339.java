class DummyClass_98339 {
    @Test
    public void expectAnyPath(){
        client.expect(HttpMethod.GET)
                .thenReturn("woh");

        Unirest.get(path).asEmpty();

        client.verifyAll();
    }

}