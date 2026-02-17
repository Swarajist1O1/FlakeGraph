class DummyClass_19469 {
	@Test public void testBug236425() throws Exception {
			public Iterator<INode> iterator() {
				return Iterators.filter(node.getAsTreeIterable().iterator(), new Predicate<INode>() {
					@Override
					public boolean apply(INode input) {
						return input.getSyntaxErrorMessage() != null;
					}

}