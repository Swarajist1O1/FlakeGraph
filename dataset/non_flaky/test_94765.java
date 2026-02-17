class DummyClass_94765 {
    @Test
    public void testGenericsListenerDefaultConstructorThrows() {
        try {
            GenericsListener<Integer, AISMessage01> gl = new GenericsListener<>();
            fail("exception not thrown, resolved to " + gl.messageType);
        } catch (IllegalStateException ise) {
            assertEquals("Cannot resolve generic type <T>, use constructor with Class<T> param.", ise.getMessage());
        } catch (Exception e) {
            fail("unexpected exception thrown: " + e.getMessage());
        }
    }

}