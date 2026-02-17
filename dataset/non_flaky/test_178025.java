class DummyClass_178025 {
    @Test
    public void adapterSize_nonVisibleRowInserted() {
        int itemCount = 4;
        ArrayObjectAdapter adapter = new ArrayObjectAdapter(presenterSelector);
        adapter.add(new SectionRow("section 1"));
        for (int i = 0; i < itemCount; i++) {
            HeaderItem headerItem = new HeaderItem(i, "header "+i);
            adapter.add(new ListRow(headerItem, createListRowAdapter()));
        }

        ListRowDataAdapter listRowDataAdapter = new ListRowDataAdapter(adapter);
        assertEquals(5, listRowDataAdapter.size());

        List<DividerRow> invisibleRows = new ArrayList<>();
        invisibleRows.add(new DividerRow());
        invisibleRows.add(new DividerRow());

        listRowDataAdapter.registerObserver(dataObserver);
        adapter.addAll(adapter.size(), invisibleRows);
        verify(dataObserver, times(0)).onItemRangeInserted(anyInt(), anyInt());
        assertEquals(5, listRowDataAdapter.size());

        adapter.add(new DividerRow());
        verify(dataObserver, times(0)).onItemRangeInserted(anyInt(), anyInt());
        assertEquals(5, listRowDataAdapter.size());

        adapter.add(new ListRow(new HeaderItem(0, "Header 5"), createListRowAdapter()));
        verify(dataObserver, times(1)).onItemRangeInserted(5, 4);
        assertEquals(9, listRowDataAdapter.size());
    }

}