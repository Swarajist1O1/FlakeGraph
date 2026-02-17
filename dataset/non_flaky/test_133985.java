class DummyClass_133985 {
    @Test
    public void testLayoutComparator(){
        Layout l1 = mock(Layout.class);
        Layout l2 = mock(Layout.class);

        when(l1.getEpoch()).thenReturn(1L);
        when(l2.getEpoch()).thenReturn(2L);

        TreeSet<Layout> descendingOrder = new TreeSet<>(Layout.LAYOUT_COMPARATOR);
        descendingOrder.add(l1);
        descendingOrder.add(l2);

        assertEquals(l2.getEpoch(), descendingOrder.first().getEpoch());
    }

}