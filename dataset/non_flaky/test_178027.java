class DummyClass_178027 {
    @Test
    public void adapterSize_nonVisibleRowRemoved() {
        int itemCount = 4;
        ArrayObjectAdapter adapter = new ArrayObjectAdapter(presenterSelector);
        adapter.add(new SectionRow("section 1"));
        for (int i = 0; i < itemCount; i++) {
            HeaderItem headerItem = new HeaderItem(i, "header "+i);
            adapter.add(new ListRow(headerItem, createListRowAdapter()));
        }

        ListRowDataAdapter listRowDataAdapter = new ListRowDataAdapter(adapter);
        assertEquals(5, listRowDataAdapter.size());
        adapter.add(new DividerRow());
        assertEquals(5, listRowDataAdapter.size());

        listRowDataAdapter.registerObserver(dataObserver);
        adapter.removeItems(4, 1);
        verify(dataObserver, times(1)).onItemRangeRemoved(4, 1);
        assertEquals(4, listRowDataAdapter.size());

        adapter.removeItems(4, 1);
        verify(dataObserver, times(0)).onItemRangeInserted(anyInt(), anyInt());
        assertEquals(4, listRowDataAdapter.size());
    }

}