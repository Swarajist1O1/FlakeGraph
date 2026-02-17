class DummyClass_178024 {
    @Test
    public void adapterSize_visibleRowInserted() {
        int itemCount = 4;
        ArrayObjectAdapter adapter = new ArrayObjectAdapter(presenterSelector);
        adapter.add(new SectionRow("section 1"));
        for (int i = 0; i < itemCount; i++) {
            HeaderItem headerItem = new HeaderItem(i, "header "+i);
            adapter.add(new ListRow(headerItem, createListRowAdapter()));
        }

        ListRowDataAdapter listRowDataAdapter = new ListRowDataAdapter(adapter);
        assertEquals(5, listRowDataAdapter.size());

        listRowDataAdapter.registerObserver(dataObserver);
        List<ListRow> visibleRows = new ArrayList<>();
        visibleRows.add(new ListRow(new HeaderItem(0, "Header 51"), createListRowAdapter()));
        visibleRows.add(new ListRow(new HeaderItem(0, "Header 52"), createListRowAdapter()));
        visibleRows.add(new ListRow(new HeaderItem(0, "Header 53"), createListRowAdapter()));
        adapter.addAll(2, visibleRows);
        verify(dataObserver, times(1)).onItemRangeInserted(2, 3);
        assertEquals(8, listRowDataAdapter.size());
    }

}