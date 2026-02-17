class DummyClass_110153 {
	@Test(expected = NoSuchElementException.class)
	public void iterateBeyondInnerList() {
		NestedIterator<String> nestedIterator = new NestedIterator<>(
				Collections.singletonList(Collections.<String> emptyList()));
		nestedIterator.next();
	}

}