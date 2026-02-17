class DummyClass_19479 {
	@Test public void testGetLeafNodes_01() {
			public Iterator<INode> iterator() {
				return new AbstractIterator<INode>() {

					private BidiTreeIterator<AbstractNode> delegate = node.basicIterator();
					
					@Override
					protected INode computeNext() {
						if (delegate.hasPrevious())
							return delegate.previous();
						return endOfData();
					}
				};
			}

}