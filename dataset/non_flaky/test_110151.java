class DummyClass_110151 {
	@Test
	public void testIteration() {
		List<String> list1 = new ArrayList<String>();
		list1.add("1");
		list1.add("2");
		List<String> list2 = new ArrayList<String>();
		list2.add("3");
		List<String> list3 = new ArrayList<String>();
		List<String> list4 = new ArrayList<String>();
		list4.add("4");

		List<List<String>> listOfLists = new ArrayList<>();
		listOfLists.add(list1);
		listOfLists.add(list2);
		listOfLists.add(list3);
		listOfLists.add(list4);

		NestedIterator<String> nestedIterator = new NestedIterator<>(
				listOfLists);

		assertTrue(nestedIterator.hasNext());
		assertEquals("1", nestedIterator.next());
		assertTrue(nestedIterator.hasNext());
		assertEquals("2", nestedIterator.next());
		assertTrue(nestedIterator.hasNext());
		assertEquals("3", nestedIterator.next());
		assertTrue(nestedIterator.hasNext());
		assertEquals("4", nestedIterator.next());
		assertEquals(false, nestedIterator.hasNext());
	}

}