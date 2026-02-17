class DummyClass_94758 {
    @Test
    public void testParametrizedConstructor() {

        ExtendedBasicListener ebl = new ExtendedBasicListener();

        assertNull(ebl.get());
        assertEquals(ebl.messageType, AISMessage01.class);
    }

}