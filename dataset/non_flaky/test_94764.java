class DummyClass_94764 {
    @Test
    public void testGenericsListener() {

        GenericsListener<Integer, AISMessage01> gl = new GenericsListener<>(AISMessage01.class);
        gl.sentenceRead(AIS_01);

        assertEquals(gl.received.toString(), MSG_01.toString());
        assertEquals("1", gl.dummy(1));
    }

}