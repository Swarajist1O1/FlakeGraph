class DummyClass_122598 {
    @Test
    public void test() {
        Templar templar = new Templar("x y <%= foo %>, some other <%=bar%> text");
        templar.set("foo", "fidelity")
                .set("bar", "halimov")
                .set("not", "used");

        assertEquals("x y fidelity, some other halimov text", templar.resolve());
    }

}