class DummyClass_178029 {
    @Test
    public void customObjectAdapterTest() {
        int itemCount = 4;
        ArrayObjectAdapter adapter = new CustomAdapter(presenterSelector);
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
        adapter.removeItems(3, 3);
        verify(dataObserver, times(1)).onChanged();
        assertEquals(3, listRowDataAdapter.size());

        Mockito.reset(dataObserver);
        adapter.add(new DividerRow());
        verify(dataObserver, times(1)).onChanged();
        assertEquals(3, listRowDataAdapter.size());
    }

}