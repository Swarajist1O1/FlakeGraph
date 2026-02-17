class DummyClass_77677 {
    @Test public void shrinkingBooleanFromTrue() {
        @Property public void shouldHold(boolean b) {
            values.add(b);

            assumeTrue(b);
            fail();
        }

}