class DummyClass_177229 {
    @Test
        public void updateCandidates(List<Endpoint> candidates) {
            this.candidates = candidates;
            selectedCandidates = ImmutableList.copyOf(candidates);
        }

}