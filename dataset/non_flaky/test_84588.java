class DummyClass_84588 {
    @Test
    public void testMissingSequenceNumber() throws Exception {
        String[] names = {"c", "b-1", "a"};
        ZNodeName zname;

        final Collection<ZNodeName> nodeNames = Arrays.asList(names).stream()
            .map(name -> new ZNodeName(name)).sorted().collect(Collectors.toList());

        final Iterator<ZNodeName> it = nodeNames.iterator();

        zname = it.next();
        assertEquals("b-1", zname.getName());
        assertEquals("b", zname.getPrefix());
        assertEquals(Integer.valueOf(1), zname.getSequence().get());

        zname = it.next();
        assertEquals("a", zname.getName());
        assertEquals("a", zname.getPrefix());
        assertFalse(zname.getSequence().isPresent());

        zname = it.next();
        assertEquals("c", zname.getName());
        assertEquals("c", zname.getPrefix());
        assertFalse(zname.getSequence().isPresent());
    }

}