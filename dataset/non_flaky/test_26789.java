class DummyClass_26789 {
	@Test
	public void testUpdateDataBaseWithTrelloContent() {
		List<DayPairs> pairsList = getPairsListFromDevs(getStandardDevs());
		DayPairs oldPairs = new DayPairs();
		oldPairs.setDate(pairsList.get(0).getDate());
		oldPairs.addPair("oldTrack", new Pair());
		when(trelloPairsRepository.findByDate(pairsList.get(0).getDate())).thenReturn(Arrays.asList(oldPairs));
		when(trelloPairsRepository.findByDate(pairsList.get(1).getDate())).thenReturn(Arrays.asList());

		subject.updateDataBaseWithTrelloContent(pairsList);

		verify(trelloPairsRepository, atLeast(1)).save(pairsList.get(0));
		verify(trelloPairsRepository, atLeast(1)).save(pairsList.get(1));
	}

}