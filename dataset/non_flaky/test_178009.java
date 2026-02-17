class DummyClass_178009 {
    @Test
    public void testFastRewindToMaxThenReset() {
        PlaybackControlsRow row = new PlaybackControlsRow();
        glue.setControlsRow(row);
        SparseArrayObjectAdapter adapter = (SparseArrayObjectAdapter)
                row.getPrimaryActionsAdapter();
        PlaybackControlsRow.MultiAction playPause = (PlaybackControlsRow.MultiAction) adapter
                .lookup(PlaybackControlGlue.ACTION_PLAY_PAUSE);
        PlaybackControlsRow.MultiAction fastForward = (PlaybackControlsRow.MultiAction) adapter
                .lookup(PlaybackControlGlue.ACTION_FAST_FORWARD);
        PlaybackControlsRow.MultiAction rewind = (PlaybackControlsRow.MultiAction) adapter
                .lookup(PlaybackControlGlue.ACTION_REWIND);

        assertFalse(glue.isMediaPlaying());
        glue.onActionClicked(playPause);
        assertTrue(glue.isMediaPlaying());
        assertEquals(PlaybackControlGlue.PLAYBACK_SPEED_NORMAL, glue.getCurrentSpeedId());
        assertEquals(0, fastForward.getIndex());
        assertEquals(0, rewind.getIndex());

        // click multiple times to reach PLAYBACK_SPEED_FAST_L2
        glue.onActionClicked(rewind);
        assertEquals(-PlaybackControlGlue.PLAYBACK_SPEED_FAST_L0, glue.getCurrentSpeedId());
        assertEquals(0, fastForward.getIndex());
        assertEquals(1, rewind.getIndex());
        glue.onActionClicked(rewind);
        assertEquals(-PlaybackControlGlue.PLAYBACK_SPEED_FAST_L1, glue.getCurrentSpeedId());
        assertEquals(0, fastForward.getIndex());
        assertEquals(2, rewind.getIndex());
        glue.onActionClicked(rewind);
        assertEquals(-PlaybackControlGlue.PLAYBACK_SPEED_FAST_L2, glue.getCurrentSpeedId());
        assertEquals(0, fastForward.getIndex());
        assertEquals(3, rewind.getIndex());
        glue.onActionClicked(rewind);
        assertEquals(-PlaybackControlGlue.PLAYBACK_SPEED_FAST_L2, glue.getCurrentSpeedId());
        assertEquals(0, fastForward.getIndex());
        assertEquals(3, rewind.getIndex());

        // press playPause again put it back to play
        glue.onActionClicked(playPause);
        assertEquals(PlaybackControlGlue.PLAYBACK_SPEED_NORMAL, glue.getCurrentSpeedId());
        assertEquals(0, fastForward.getIndex());
        assertEquals(0, rewind.getIndex());
    }

}