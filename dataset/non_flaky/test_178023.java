class DummyClass_178023 {
    @Test
    public void adapterSize_nonVisibleRowPresent() {
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
        adapter.addAll(5, invisibleRows);
        verify(dataObserver, times(0)).onItemRangeInserted(anyInt(), anyInt());
        assertEquals(5, listRowDataAdapter.size());
    }

}