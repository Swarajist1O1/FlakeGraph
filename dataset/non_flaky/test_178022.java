class DummyClass_178022 {
    @Test
    public void itemRangeChangedTest() {
        int itemCount = 4;
        ArrayObjectAdapter adapter = new ArrayObjectAdapter(presenterSelector);
        adapter.add(new SectionRow("section 1"));
        for (int i = 0; i < itemCount; i++) {
            HeaderItem headerItem = new HeaderItem(i, "header "+i);
            adapter.add(new ListRow(headerItem, createListRowAdapter()));
        }

        ListRowDataAdapter listRowDataAdapter = new ListRowDataAdapter(adapter);
        listRowDataAdapter.registerObserver(dataObserver);
        SectionRow sectionRow = new SectionRow("section 11");
        adapter.replace(0, sectionRow);

        verify(dataObserver, times(1)).onItemRangeChanged(0, 1);
        assertEquals(5, listRowDataAdapter.size());
    }

}