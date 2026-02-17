class DummyClass_70864 {
    @Test
    public void currentState() {
        for (State state : State.values()) {
            tracker.changeState(state, time.milliseconds());
            assertEquals(state, tracker.currentState());
        }
    }

}