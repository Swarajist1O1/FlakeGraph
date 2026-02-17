class DummyClass_110154 {
	@Test(expected = NoSuchElementException.class)
	public void iterateBeyondOuterList() {
		NestedIterator<String> nestedIterator = new NestedIterator<>(
				Collections.<List<String>> emptyList());
		nestedIterator.next();
	}

}