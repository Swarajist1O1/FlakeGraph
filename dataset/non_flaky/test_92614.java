class DummyClass_92614 {
    @Test
    public void testValueOfURLWithValueType() throws IOException {
        when(objectReader.forType((Class<?>) any())).thenReturn(objectReader);
        when(objectReader.readValue((URL) any())).thenReturn(pojo);
        when(objectReader.readValue((URL) any(), (Class<?>) any())).thenCallRealMethod();

        URL source = new URL("http://www.test.com");
        POJO result = objectReader.readValue(source, POJO.class);

        assertEquals(result, pojo);
        verify(objectReader).forType(POJO.class);
        verify(objectReader).readValue(source);
    }

}