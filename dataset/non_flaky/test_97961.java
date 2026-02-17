class DummyClass_97961 {
	@Test public void demoInterval() throws Exception {
	public void testLongObservable(Observable<Long> o, final String testname) throws Exception {
		final List<Long> l = new ArrayList<Long>();
		Action1<Long> onNext = new Action1<Long>() {
			public void call(Long i) { 
				l.add(i);
				System.out.println(testname + " got " + i);
			}

}