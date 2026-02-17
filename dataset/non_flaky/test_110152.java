class DummyClass_110152 {
	@Test(expected = UnsupportedOperationException.class)
	public void removeNotSupported() {
		NestedIterator<String> nestedIterator = new NestedIterator<>(
				Collections.singletonList(Collections.singletonList("Test")));
		nestedIterator.remove();
	}

}