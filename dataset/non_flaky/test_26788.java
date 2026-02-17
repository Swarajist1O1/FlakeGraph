class DummyClass_26788 {
	@Test(expected = RuntimeException.class)
	public void testUpdateDataBaseWithTrelloContentWithException() {
		List<DayPairs> pairsList = getPairsListFromDevs(getStandardDevs());
		when(trelloPairsRepository.findByDate(pairsList.get(2).getDate())).thenReturn(pairsList);

		subject.updateDataBaseWithTrelloContent(pairsList);
	}

}