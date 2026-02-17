class DummyClass_162402 {
    @Test
    public void simpleTest() {

        for (int i = 0; i < 3; i++) {
            clients[i].incr("somekey");

            assertEquals("Each redis instance is separate", "1", clients[i].get("somekey"));
        }
    }

}