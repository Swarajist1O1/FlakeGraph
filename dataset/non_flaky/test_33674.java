class DummyClass_33674 {
    @Test
    public void testIssue1463() {
        String str = doubleDeserialization(wenshao);
        try {
            wenshao = JSON.parseObject(str, Person.class);
        } catch (Throwable ex) {
            Assert.assertEquals(ex.getCause() instanceof NullPointerException, false);
        }
    }

}